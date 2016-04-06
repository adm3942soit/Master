    def baseName="Gradle-jobs"
    def theInfoName = "${WORKSPACE}/repositories.txt"
    File theInfoFile = new File(theInfoName)
    def lines
    def linesNmbr=0
    if (!theInfoFile.exists()) {
        println "File does not exist!!!!!"

    } else {
        println "File exist!!!!!"
        lines=theInfoFile.
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
        def i=1
    lines.each{
       job{"$baseName"+"/"+"$i"}{
           scm{
              gitHub(lines[$i])
              i++
           }
       }
    }
    }
