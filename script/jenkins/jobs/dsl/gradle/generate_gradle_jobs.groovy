node {
//def theInfoName = "${JENKINS_HOME}"+"jobs/"+"$JOB_NAME"+"workspace/repositories.txt"
    def theInfoName = "Master/repositories.txt"
    File theInfoFile = new File(theInfoName)

    sh "ls"
//def lines = theInfoFile.readLines()

    if (!theInfoFile.exists()) {
        println "File does not exist!!!!!"

    } else {

        theInfoFile.eachLine { line ->

            if (line.trim().size() == 0) {
                return null

            } else {
                println "${line}"
            }

        }
    }
}