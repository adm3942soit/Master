    def theInfoName = "${WORKSPACE}/repositories.txt"
    File theInfoFile = new File(theInfoName)
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
            }

        }
    }
