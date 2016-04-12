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
           |cd $WORKSPACE/Master
           |git remote set-url –push origin $gerritUrl HEAD:refs/for/master
           |git fetch -p origin
           |git push –-mirror
           |git remote add Master $gerritUrl
           |git push -f –tags Master refs/heads/*:refs/for/*
           |set -x'''.stripMargin())
    }
/*
    |git clone --mirror $gitUrl
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
*/

}


