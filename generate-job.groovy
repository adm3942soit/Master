job("Demo job") {
//node{
    def urlRepo='https://github.com/adm3942soit/test1.git'
    scm {
        git {
            remote {
                url urlRepo
            }
            branch 'master'
            shallowClone true
        }
    }
    steps {
        stage 'Checkout'
        git url: urlRepo
        def v = version(readFile('pom.xml'))
        if (v) {
            echo "Building version ${v}"
        }
        stage 'Package'
        def mvnHome = tool 'ADOP Maven'
        sh "${mvnHome}/bin/mvn -B verify"
        sh "${mvnHome}/bin/mvn clean package"
        stage 'Deploy'
        sh "${mvnHome}/bin/mvn deploy"
    }
}
@NonCPS
def version(text) {
    def matcher = text =~ '<version>(.+)</version>'
    matcher ? matcher[0][1] : null
}