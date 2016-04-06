
job("Gradle-jobs"){
//def theInfoName = "${JENKINS_HOME}"+"jobs/"+"$JOB_NAME"+"workspace/repositories.txt"
    def theInfoName = "repositories.txt"
    File theInfoFile = new File(theInfoName)
    scm{
        git('https://github.com/adm3942soit/Master.git')
    }
    steps {
    shell(readFileFromWorkspace(theInfoName))
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
}