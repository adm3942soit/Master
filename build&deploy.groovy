node {
    stage 'Checkout'
    git url: 'https://github.com/adm3942soit/Master.git'
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    if (matcher) {
        echo "Building version ${matcher[0][1]}"
    }
    stage 'Package'
    def mvnHome = tool 'ADOP Maven'
    sh "${mvnHome}/bin/mvn -B verify"
    sh "${mvnHome}/bin/mvn clean package"
    stage 'Deploy'
    sh "${mvnHome}/bin/mvn deploy"
}//node