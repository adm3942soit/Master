def nameJob="GerritRepoCopy"
def changeBranch = "change-${GERRIT_CHANGE_NUMBER}-${GERRIT_PATCHSET_NUMBER}"

job("$nameJob"){
    scm{
        git('https://github.com/adm3942soit/Master.git')
    }
    steps{
        shell('''set +x
        |git fetch origin ${GERRIT_REFSPEC}:${changeBranch}
        |git checkout ${changeBranch}
        |ssh -p 29418 jenkins@gerrit
        |git push ssh://jenkins@gerrit:29418/Master HEAD:refs/master
       |set -x'''.stripMargin())
    }
}


