def PROJECT_NAME="Master_Build"
def WORKSPACE_NAME="Master_Build"
// Folders
def workspaceFolderName = "${WORKSPACE_NAME}"
def projectFolderName = "${PROJECT_NAME}"
def Parent_Dir="$JENKINS_HOME"+"/job"+"/Master_Build/"
// Jobs
def buildAppJob = freeStyleJob("Master_Build")
def deployJob = freeStyleJob("Master_Deploy")
//Number_Parent_Build_URL
def Parent_Build_URL="$JENKINS_HOME"+"/Master_Build/"
def PARENT_BUILD_NUMBER=""
buildAppJob.with{
    scm{
        git('https://github.com/adm3942soit/Master.git')
    }
    steps{
        maven{
            mavenInstallation("maven")
            goals("clean package")
        }
    }
}

// Variables
def referenceAppGitRepo = "master"
//ssh://jenkins@gerrit:29418/${PROJECT_NAME}/ + referenceAppGitRepo
def referenceAppGitUrl = "https://github.com/adm3942soit/Master.git"


buildAppJob.with {
    description("This job builds Master application")
    wrappers {
        preBuildCleanup()
        injectPasswords()
        maskPasswords()
        sshAgent("adop-jenkins-master")
    }
    scm {
        git {
            remote {
                url(referenceAppGitUrl)
                credentials("adop-jenkins-master")
            }
            branch("*/master")
        }
    }
    environmentVariables {
        env('WORKSPACE_NAME',workspaceFolderName)
        env('PROJECT_NAME',projectFolderName)
    }
    label("java8")
    triggers{
        gerrit{
            events{
                refUpdated()
            }
            configure { gerritxml ->
                gerritxml / 'gerritProjects' {
                    'com.sonyericsson.hudson.plugins.gerrit.trigger.hudsontrigger.data.GerritProject' {
                        compareType("PLAIN")
                        //projectFolderName + "/" +
                        pattern(referenceAppGitRepo)
                        'branches' {
                            'com.sonyericsson.hudson.plugins.gerrit.trigger.hudsontrigger.data.Branch' {
                                compareType("PLAIN")
                                pattern("master")
                            }
                        }
                    }
                }
                gerritxml / serverName("ADOP Gerrit")
            }
        }
    }
    steps {
        maven{
            goals('clean install -DskipTests')
            mavenInstallation("maven")
        }
        Parent_Build_URL="$BUILD_URL"
        PARENT_BUILD_NUMBER="$BUILD_NUMBER"
    }
    publishers{
        archiveArtifacts("**/*")
        downstreamParameterized {
            //
            trigger("Master_Deploy") {
                condition("UNSTABLE_OR_BETTER")
                parameters {
                    predefinedProp("B", '${BUILD_NUMBER}')
                    predefinedProp("PARENT_BUILD", '${BUILD_URL}')
                }
            }

        }
    }

}
queue("Master_Build")
deployJob.with {
    description("This job deploys the java reference application to the CI environment")
    parameters {
        stringParam("B", '${PARENT_BUILD_NUMBER}', "Parent build number")
        stringParam("PARENT_BUILD", "$BUILD_URL", "Parent build name")
        stringParam("ENVIRONMENT_NAME", "CI", "Name of the environment.")
    }

    wrappers {
        preBuildCleanup()
        injectPasswords()
        maskPasswords()
        sshAgent("adop-jenkins-master")
    }
    environmentVariables {
        env('WORKSPACE_NAME', workspaceFolderName)
        env('PROJECT_NAME', projectFolderName)
        //env('PARENT_BUILD', "$Parent_Build_URL")
        //env('PARENT_BUILD_NUMBER',"$PARENT_BUILD_NUMBER")
    }
    label("docker")
    steps {
        copyArtifacts("Master_Build") {
            buildSelector {
                buildNumber('${B}')
            }
        }


       /* shell('''set +x
            |jobsdir=/var/lib/jenkins/jobs

            |if [ -e "$jobsdir/$jobname/lastSuccessful/archive/" ]; then
            mkdir -p $target

            (
                    cd $jobsdir/$jobname/lastSuccessful/archive/
                    rsync -a $artefacts_pattern $target/ > /dev/null 2>&1 || true
            )
            fi
        |set -x'''.stripMargin())*/

        shell('''set +x
            |export SERVICE_NAME="$(echo ${PROJECT_NAME} | tr '/' '_')_${ENVIRONMENT_NAME}"
            |docker cp ${WORKSPACE}/target/master.war  ${SERVICE_NAME}:/usr/local/tomcat/webapps/
            |docker restart ${SERVICE_NAME}
            |COUNT=1
            |while ! curl -q http://${SERVICE_NAME}:8080/master -o /dev/null
            |do
            |  if [ ${COUNT} -gt 10 ]; then
            |      echo "Docker build failed even after ${COUNT}. Please investigate."
            |      exit 1
            |  fi
            |  echo "Application is not up yet. Retrying ..Attempt (${COUNT})"
            |  sleep 5
            |  COUNT=$((COUNT+1))
            |done
            |echo "=.=.=.=.=.=.=.=.=.=.=.=."
            |echo "=.=.=.=.=.=.=.=.=.=.=.=."
            |echo "Environment URL (replace PUBLIC_IP with your public ip address where you access jenkins from) : http://${SERVICE_NAME}.PUBLIC_IP.xip.io/master"
            |echo "=.=.=.=.=.=.=.=.=.=.=.=."
            |echo "=.=.=.=.=.=.=.=.=.=.=.=."
            |set -x'''.stripMargin())
    }
}
queue("Master_Deploy")
// Views
def pipelineView = buildPipelineView("Master_Application")
pipelineView.with{
    title('Master_Application Pipeline')
    displayedBuilds(5)
    selectedJob("Master_Build")
    showPipelineParameters()
    showPipelineDefinitionHeader()
    refreshFrequency(5)
}
queue("Master_Application")
