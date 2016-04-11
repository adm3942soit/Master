def nameJob="GerritRepoCopy"
def PROJECT_NAME="Master"
def gerritUrl="ssh://jenkins@gerrit:29418/${PROJECT_NAME}"

job("$nameJob"){
    scm{
        git('https://github.com/adm3942soit/Master.git')
    }
    steps{
        shell('''set +x
        |git remote set-url origin $gerritUrl
        |ssh -p 29418 jenkins@gerrit
        |git clone $gerritUrl
       |set -x'''.stripMargin())
    }
    //|git push ssh://jenkins@gerrit:29418/Master HEAD:refs/master
}


