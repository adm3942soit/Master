node{
    sh "git clone ssh://jenkins@gerrit:29418/All-Projects"
    sh "git clone https://github.com/adm3942soit/Master.git"
    sh "cp -r All-Projects Master"
    sh "cd Master/"
    sh "git add All-Projects"
    sh "git commit -a -m"

}

//https://github.com/adm3942soit/Master.git