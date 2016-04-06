def theInfoName = "repositories.txt"
File theInfoFile = new File(theInfoName)

def lines = theInfoFile.readLines()

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
