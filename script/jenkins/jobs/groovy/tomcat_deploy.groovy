def deployApp(war, catalinaBase, httpPort){
    sh "${catalinaBase}/bin/shudown.sh || :"
    sh "rm -rf ${catalinaBase}/webapps/ROOT"
    sh "rm -rf ${catalinaBase}/webapps/ROOT.war"
    sh "cp -rf ${war} ${catalinaBase}/webapps/ROOT.war"
    sh "${catalinaBase}/bin/startup.sh"
    echo "$catalinaBase sercer restarted with new webapp $war, see htttp://localhost:$httpPort"
    retry(count: 5){
        sh "sleep 5 && curl http://localhost:$httpPort/index.jsp"
    }

}
/*
sh "call ${JBOSS_HOME}/bin/standalone.bat"
sh "sleep 5 && curl http://localhost:8082/master/"
*/
def shutdownApp(catalinaBase){
    sh "${catalinaBase}/bin/shutdown.sh || :"
    echo "$catalinaBase server is stopped"
}