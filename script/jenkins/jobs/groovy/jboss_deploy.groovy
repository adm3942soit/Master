def jbossHome = "C:\\wildfly-10.0.0.Beta1"
// Jobs
//def buildAppJob = freeStyleJob("Master_Build")
//def deployAppJob = freeStyleJob("Master_Deploy")

node{
deployApp("target/master.war", jbossHome, 8082)
}

/*
buildAppJob.with{
    scm{
        git('https://github.com/adm3942soit/Master.git')
    }
    steps{
        maven{
            mavenInstallation("ADOP Maven")
            goals("clean package install wildfly:deploy")
        }
    }
}

queue("Master_Build")

deployAppJob.with{

}
*/
def deployApp(war, jbossHome, httpPort){
    sh "${jbossHome}/bin/standalone.sh"
    echo "$jbossHome sercer restarted with new webapp $war, see htttp://localhost:$httpPort"
    retry(count: 5){
        sh "sleep 5 && curl http://localhost:$httpPort/master/"
    }

}
