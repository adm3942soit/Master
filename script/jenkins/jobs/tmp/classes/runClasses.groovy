class TestIt {
    def static helloStatic() {
        println "[STATIC] - Hello"
    }

    def hello() {
        println "[NORMAL] - Hello"
    }
}

def runIt() {
    println "Starting test"
    println "-------------"
    TestIt _test = new TestIt()
    _test.hello();
    TestIt.helloStatic();
    println "-------------"
    println "Done"
}

runIt();
TestIt.helloStatic();