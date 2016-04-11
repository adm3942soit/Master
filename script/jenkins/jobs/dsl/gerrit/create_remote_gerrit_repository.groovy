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
        |git remote set-url origin $gerritUrl
        |git remote -v
        |## echo "remote set-url origin $gerritUrl"
        |git clone $gerritUrl
        |## echo "clone $gerritUrl"
        |git push $gerritUrl HEAD:refs/for/master
        |## echo "push $gerritUrl"
        |ssh -p 29418 jenkins@gerrit
        |ssh -p 29418 jenkins@gerrit gerrit create-project MasterCopy
        |cd $WORKSPACE
        |ls
       |set -x'''.stripMargin())
    }
    //
}


