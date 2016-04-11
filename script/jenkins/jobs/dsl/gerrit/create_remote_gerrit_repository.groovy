def nameJob="GerritRepoCopy"
def PROJECT_NAME="Master"
def PROJECT_GERRIT_NAME="MasterCopy"
def gerritUrl="ssh://jenkins@gerrit:29418/${PROJECT_GERRIT_NAME}"

job("$nameJob"){
    scm{
        git('https://github.com/adm3942soit/Master.git')
    }
    steps{
        shell('''set +x
        |git clone
        |git remote set-url origin $gerritUrl
        |ssh -p 29418 jenkins@gerrit
        |git clone $gerritUrl
        |cd $WORKSPACE
        |ls
       |set -x'''.stripMargin())
    }
    //|git push ssh://jenkins@gerrit:29418/Master HEAD:refs/master
}


