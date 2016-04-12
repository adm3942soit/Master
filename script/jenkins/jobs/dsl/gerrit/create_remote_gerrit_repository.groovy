def nameJob="GerritRepoCopy"
def PROJECT_NAME="Master"
def PROJECT_GERRIT_NAME="MasterCopy"
def gerritUrl="ssh://jenkins@gerrit:29418/${PROJECT_GERRIT_NAME}"
def gitUrl="https://github.com/adm3942soit/Master.git"
job("$nameJob"){
    wrappers {
        preBuildCleanup()
        injectPasswords()
        maskPasswords()
        sshAgent("adop-jenkins-master")
    }
    scm{
        git {
            remote {
                url(gitUrl)
                credentials("adop-jenkins-master")
            }
            branch("*/master")
        }
    }


    steps{
        shell('''set +x
|#git clone --bare  https://github.com/adm3942soit/Master.git
|[[ -s '/usr/local/lib/rvm' ]] && source '/usr/local/lib/rvm\'
|cd $WORKSPACE
|#ls
|#cd $WORKSPACE/Master.git
|ls
|git remote set-url --add origin ssh://jenkins@gerrit:29418/Master.git
|#git fetch -p origin
|#git commit -m 'initial commit\'
|#ssh -p 29418 jenkins@gerrit gerrit create-project -n Master
|git config credential.helper store
|git config --global push.default simple
|git remote add --mirror=push github ssh://jenkins@gerrit:29418/Master.git
|git remote -v
|#git push ssh://jenkins@gerrit:29418/Master.git
|git clone ssh://jenkins@gerrit:29418/Master.git
|set -x'''.stripMargin())
    }
/*

*/

/*
        |cd $WORKSPACE/Master/
        |git push –mirror $gerritUrl
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
        $ git remote set-url --push origin ssh://raphink@git.fedorahosted.org/git/augeas.git
$ git remote show origin
*/

}
queue(nameJob)

