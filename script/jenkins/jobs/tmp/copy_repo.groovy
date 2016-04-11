node{
    sh "git clone ssh://jenkins@gerrit:29418/All-Projects"
    sh "git clone https://github.com/adm3942soit/Master.git"
    sh "ls"
    sh "mv All-Projects/ Master/All-Projects"
    //sh "ls"
  //  def projectFolderName = "CopyRepo"
   // def pipelineView = buildPipelineView(projectFolderName + "/Java_Reference_Application")
  // steps {
   // sh "set +x"

/*
    sh "dir Master/All-Projects"
    sh "chmod -R a+x+X Master"
    sh "pwd"
       sh  "ls -l"
    sh  "cd Master/"
    sh "pwd"
    sh  "ls -l"
*/
   // sh  "set -x"
}

    /*pipelineView.with{
        title('Reference Application Pipeline')

        displayedBuilds(5)
        selectedJob(projectFolderName + "/Reference_Application_Build")
        showPipelineParameters()
        showPipelineDefinitionHeader()
        refreshFrequency(5)
    }*/

    //sh "git add All-Projects"
    //sh "git commit -a -m"
    //sh "git push"


//}

//https://github.com/adm3942soit/Master.git