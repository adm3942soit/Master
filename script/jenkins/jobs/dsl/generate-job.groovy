job("Demo build job") {
    scm {
        git {
            remote {
                url 'https://github.com/lexandro/restapi-demo.git'
            }
            branch 'master'
            shallowClone true
        }
    }
    steps {
        maven('compile')
    }
}