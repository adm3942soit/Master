def PROJECT_NAME="Master"
def WORKSPACE_NAME="Master"
// Folders
def workspaceFolderName = "${WORKSPACE_NAME}"
def projectFolderName = "${PROJECT_NAME}"

// Jobs
def buildAppJob = freeStyleJob("Master_Build")
def deployJob = freeStyleJob("Master_Deploy")

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
    }
    publishers{
        archiveArtifacts("**/*")
        downstreamParameterized {
            //
            trigger("Master_Deploy") {
                condition("UNSTABLE_OR_BETTER")
                parameters {
                    predefinedProp("B", '${PARENT_BUILD_NUMBER}')
                    predefinedProp("PARENT_BUILD", '${PARENT_BUILD}')
                }
            }

        }
    }
    def Parent_Dir="$JENKINS_HOME"+"/job"+"/Master_Build/"+"${PARENT_BUILD_NUMBER}"
}
queue("Master_Build")
deployJob.with {
    description("This job deploys the java reference application to the CI environment")
    parameters {
        stringParam("B", '${PARENT_BUID_NUMBER}', "Parent build number")
        stringParam("PARENT_BUILD", '$JENKINS_HOME'+"/job"+"/Master_Build/", "Parent build name")
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
        env('PARENT_BUILD', Parent_Dir)
    }
    label("docker")
    steps {
        copyArtifacts("Master_Build") {
            buildSelector {
                buildNumber('${PARENT_BUILD_NUMBER}')
            }
        }
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
