/**
 * Created by oksana.dudnik on 4/13/2016.
 */
/*
We must create freestyle job in Jenkins with name baseName and add step into build phase :
        Process Job DSLs -> Look on Filesystem->DSL scripts-> pathToThisDSLScript
(for example "src/groovy/com/java/MainScript.groovy")
*/
def baseName="EXPERIMENT"
def theInfoName = "${WORKSPACE}/repositoriesGradle.txt"
File theInfoFile = new File(theInfoName)
def lines = []
def linesNmbr=0

/*cut out from  file theInfoFile each line with url*/
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
/*Let's create jobs for each url*/
if(linesNmbr!=0) {
    def i = 0
    while (i < lines.size()) {
        def jobName = "$baseName" + "$i"
        def s = lines[i]
        def result = s.tokenize("/")
        def nameProject=""
        if (result.size > 0) {
            def s1 = result[result.size - 1]
            println(s1)
            def result1 = s1.tokenize(".")
            if (result1.size > 0) {
                nameProject = result1[0]
                println(nameProject)
            }

        }
        //copyRepoFromGitToGerrit()
        def nameGerritJob="GerritRepo_"+nameProject
        def gitUrl=lines[i]
        def gerritUrl="ssh://jenkins@gerrit:29418/"+nameProject+".git"
        job("$nameGerritJob") {
            wrappers {
                preBuildCleanup()
                injectPasswords()
                maskPasswords()
                sshAgent("adop-jenkins-master")
            }
            scm{
                git {
                    remote {
                        url(gitUrl)
                        credentials("adop-jenkins-master")
                    }
                    branch("*/master")
                }
            }
            environmentVariables {
                env('gerritUrl',gerritUrl)
                env('gitUrl',gitUrl)
                env('nameProject',nameProject)
                env('COUNT', 1)
            }

            triggers{
                if (i == 0) {
                    upstream "$baseName", 'SUCCESS'
                }

                gerrit{
                    events{
                        refUpdated()
                    }
                    configure { gerritxml ->
                        gerritxml / 'gerritProjects' {
                            'com.sonyericsson.hudson.plugins.gerrit.trigger.hudsontrigger.data.GerritProject' {
                                compareType("PLAIN")
                                pattern(nameProject)
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
            println(gerritUrl)
            steps{
                shell('''set +x
|git clone --bare  ${gitUrl} $nameProject
|cd $WORKSPACE
|ls
|cd $WORKSPACE/$nameProject
|echo ${gerritUrl}
|git remote set-url --add origin ${gerritUrl}
|#if [ ${COUNT} -lt 2 ]; then
|#ssh -p 29418 jenkins@gerrit gerrit create-project -n ${nameProject}
|#${COUNT}++
|#echo "COUNT"
|#echo $COUNT
|#fi
|git config credential.helper store
|git config --global push.default simple
|git remote add --mirror=push github ${gerritUrl}
|git remote -v
|git push ${gerritUrl}
|git clone ${gerritUrl} HEAD:refs/for/master
|git branch -r
|set -x'''.stripMargin())
            }
            publishers {

                archiveArtifacts("**/*")
                downstreamParameterized {
                    trigger("$jobName"){
                        condition("UNSTABLE_OR_BETTER")
                    }

                }
            }

        }
        queue(nameGerritJob)
        //endcopyRepoFromGitToGerrit()

        job("$jobName") {
            wrappers {
                preBuildCleanup()
                injectPasswords()
                maskPasswords()
                sshAgent("adop-jenkins-master")
            }
            println(gerritUrl)

            scm {
                //git(lines[i])
                git(gerritUrl)
            }
            label("java8")
            triggers {


                cron('@hourly')

                gerrit {
                    events {
                        refUpdated()
                    }
                    configure { gerritxml ->
                        gerritxml / 'gerritProjects' {
                            'com.sonyericsson.hudson.plugins.gerrit.trigger.hudsontrigger.data.GerritProject' {
                                compareType("PLAIN")
                                pattern(gerritUrl)
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
                def j = i
                j++
            newJobName = "$baseName" + "$j"
            publishers {

                archiveArtifacts("**/*")
                downstreamParameterized {
                    //trigger("$jobName" + "sonarJob") {
                    trigger("$newJobName"){
                        condition("UNSTABLE_OR_BETTER")
                    }

                }
            }
            }

        }
        queue(jobName)
        i++
    }
}

/* Views*/
def pipelineView = buildPipelineView("Process_EXPERIMENT")
pipelineView.with{
    title('Process_EXPERIMENT Pipeline')
    displayedBuilds(5)
    selectedJob("EXPERIMENT")
    showPipelineParameters()
    showPipelineDefinitionHeader()
    refreshFrequency(5)
}
queue("Process_EXPERIMENT")

listView('ListViewEXPERIMENT') {
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
queue("ListViewEXPERIMENT")
/*
|git clone --bare  gitUrl
|[[ -s '/usr/local/lib/rvm' ]] && source '/usr/local/lib/rvm'
*/
/*
|cd $WORKSPACE/Master.git
|ls
*/
