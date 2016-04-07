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
    while(i<lines.size()){
       def jobName="$baseName"+"$i"
       job("$jobName"){
           wrappers {
               preBuildCleanup()
               injectPasswords()
               maskPasswords()
               sshAgent("adop-jenkins-master")
           }
           scm{
                   git(lines[i])
           }
           triggers {
               cron('@hourly')
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

           steps{
               gradle('clean test',
                       '-xtest',
                       true) {
                   it / fromRootBuildScriptDir(false)
               }
           }
       }
        queue(jobName)
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
