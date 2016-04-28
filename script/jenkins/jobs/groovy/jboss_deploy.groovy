def deployApp(war, jbossHome, httpPort){
    sh "${jbossHome}/bin/standalone.sh"
    echo "$jbossHome sercer restarted with new webapp $war, see htttp://localhost:$httpPort"
    retry(count: 5){
        sh "sleep 5 && curl http://localhost:$httpPort/master/"
    }

}
