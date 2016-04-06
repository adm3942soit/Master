def PROJECT_NAME="Master_Build"
def WORKSPACE_NAME="Master_Build"
// Folders
def workspaceFolderName = "${WORKSPACE_NAME}"
def projectFolderName = "${PROJECT_NAME}"

// Variables
def referenceAppGitRepo = "master"
def referenceAppGitUrl = "https://github.com/adm3942soit/Master.git"

// Jobs
def buildAppJob = freeStyleJob("Master_Build")
//def deployJob = freeStyleJob("Master_Deploy")

buildAppJob.with{
    scm{
        git('https://github.com/adm3942soit/Master.git')
    }
    steps{
        maven{
            mavenInstallation("ADOP Maven")
            goals("clean package")
        }
    }
}



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
        env("ENVIRONMENT_NAME",'CI')
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
            mavenInstallation("ADOP Maven")
        }

    }
    publishers{
        archiveArtifacts("**/*")
/*
        downstreamParameterized {
            trigger("Master_Deploy") {
                condition("UNSTABLE_OR_BETTER")
                parameters {
                    predefinedProp("B", '${BUILD_NUMBER}')
                    predefinedProp("PARENT_BUILD", '${BUILD_URL}')
                }
            }

        }
*/
    }

}
queue("Master_Build")
/*
deployJob.with {
    description("This job deploys the java reference application to the CI environment")
    parameters {
        stringParam("B", '${PARENT_BUILD_NUMBER}', "Parent build number")
        stringParam("PARENT_BUILD", '${BUILD_URL}', "Parent build name")
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
    }
    label("docker")
    steps {
        copyArtifacts("Master_Build") {
            buildSelector {
                buildNumber('${B}')
            }
        }

        shell('''set +x
            |export SERVICE_NAME="Master_Build_CI"
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
*/
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
