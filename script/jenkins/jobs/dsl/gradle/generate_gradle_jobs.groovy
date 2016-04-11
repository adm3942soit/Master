    def baseName="Gradle-jobs"
    def theInfoName = "${WORKSPACE}/repositoriesGradle.txt"
    File theInfoFile = new File(theInfoName)
    def lines = []
    def linesNmbr=0
    if (!theInfoFile.exists()) {
        println "File does not exist!!!!!"

    } else {
        println "File exist!!!!!"

        theInfoFile.eachLine { line ->
            if (line.trim().size() == 0) {
                println "Null!!!!!"
                return null
            } else {
                println "!!!!!!"+"${line}"
                lines.add("$line")
                linesNmbr++
            }

        }
    }

    if(linesNmbr!=0){
        def i=0
    while(i<lines.size()) {
        def jobName = "$baseName" + "$i"
        job("$jobName") {
            wrappers {
                preBuildCleanup()
                injectPasswords()
                maskPasswords()
                sshAgent("adop-jenkins-master")
            }
            scm {
                git(lines[i])
            }
            triggers {

                if (i == 0) {
                    upstream "$baseName", 'SUCCESS'
                }

            cron('@hourly')
        }
        label("java8")
        triggers {
            gerrit {
                events {
                    refUpdated()
                }
                configure { gerritxml ->
                    gerritxml / 'gerritProjects' {
                        'com.sonyericsson.hudson.plugins.gerrit.trigger.hudsontrigger.data.GerritProject' {
                            compareType("PLAIN")
                            pattern(lines[i])
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
            shell('''set +x
                    |cd "$WORKSPACE/$jobName"
                    |git ls-tree HEAD
                    |git update-index --chmod=+x build.gradle
                    |git update-index --chmod=+x gradlew
            |set -x'''.stripMargin())

            gradle('clean test',
                    '-xtest',
                    true) {
                it / fromRootBuildScriptDir(false)
            }
        }
        def j = i
        j++
        newJobName = "$baseName" + "$j"
        publishers {

            archiveArtifacts("**/*")
            downstreamParameterized {
                trigger("$jobName" + "sonarJob") {
                    condition("UNSTABLE_OR_BETTER")
                }

            }
        }

        job("$jobName" + "sonarJob") {
            description 'Quality check'
            deliveryPipelineConfiguration("Code Quality", "sonar")
            scm {
                scm {
                    git(lines[i])
                }

            }
            steps {
                maven{
                    mavenInstallation("ADOP Maven")
                    goals("org.codehaus.mojo:sonar-maven-plugin:2.6:sonar -Dsonar.host.url=192.168.99.101:8080")
                }

            }
            publishers {

                archiveArtifacts("**/*")
                downstreamParameterized {
                    trigger("$newJobName") {
                        condition("UNSTABLE_OR_BETTER")
                    }

                }
            }
        }

       }
        queue(jobName)
        def sonar="$jobName" + "sonarJob"
        queue(sonar)
        i++
    }
    }
// Views
    def pipelineView = buildPipelineView("Process_Application")
    pipelineView.with{
        title('Process_Application Pipeline')
        displayedBuilds(5)
        selectedJob("Gradle-jobs")
        showPipelineParameters()
        showPipelineDefinitionHeader()
        refreshFrequency(5)
    }
    queue("Process_Application")

    listView('ListView') {
        jobs {
           regex(/$baseName[0-9]{0,3}[a-z]{0,5}[A-Z]{0,1}[a-z]{0,2}/)
        }
        columns {
            status()
            name()
            lastSuccess()
            lastFailure()
            lastDuration()
            buildButton()
        }
    }
    queue("ListView")