    def baseName="Gradle-jobs"
    def theInfoName = "${WORKSPACE}/repositoriesMVN.txt"
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
               maven{
                   mavenInstallation("ADOP Maven")
                   goals("clean package")

               }

           }
       }
        queue(jobName)
        i++
    }
    }
