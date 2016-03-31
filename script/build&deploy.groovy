node {
    stage 1 "Checkout"
    git url: 'https://github.com/adm3942soit/Master.git'
    stage 2 "Package"
    def mvnHome = tool 'ADOP Maven'
    sh "${mvnHome}/bin/mvn -B verify"
    sh "${mvnHome}/bin/mvn clean package"
    stage 3 "Deploy"
    sh "${mvnHome}/bin/mvn deploy"
}//node