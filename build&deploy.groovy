node {
    stage 'Checkout'
    git url: 'https://github.com/adm3942soit/Master.git'
    def v = version(readFile('pom.xml'))
    if (v) {
        echo "Building version ${v}"
    }
    stage 'Package'
    def mvnHome = tool 'ADOP Maven'
    sh "${mvnHome}/bin/mvn -B verify"
    sh "${mvnHome}/bin/mvn clean package"
    stage 'Deploy'
    sh "${mvnHome}/bin/mvn tomcat_deploy"
}//node
@NonCPS
def version(text) {
    def matcher = text =~ '<version>(.+)</version>'
    matcher ? matcher[0][1] : null
}