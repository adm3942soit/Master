node{
    //sh "git clone ssh://jenkins@gerrit:29418/All-Projects"
    //sh "git clone https://github.com/adm3942soit/Master.git"

    //sh "ls"
    //sh "mv All-Projects/ Master/All-Projects"
    //sh "ls"
    sh "cd /var/jenkins_home/jobs/CopyRepo/workspace/Master/"
    sh "ls"
    sh "git add All-Projects"
    sh "git commit -a -m"
    sh "git push"

}

//https://github.com/adm3942soit/Master.git