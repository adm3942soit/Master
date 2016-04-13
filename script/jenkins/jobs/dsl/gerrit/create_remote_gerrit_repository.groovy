def nameJob="GerritRepoCopy"
def gerritUrl="ssh://jenkins@gerrit:29418/Master.git"
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

    triggers{
        gerrit{
            events{
                refUpdated()
            }
            configure { gerritxml ->
                gerritxml / 'gerritProjects' {
                    'com.sonyericsson.hudson.plugins.gerrit.trigger.hudsontrigger.data.GerritProject' {
                        compareType("PLAIN")
                        pattern("Master")
                        'branches' {
                            'com.sonyericsson.hudson.plugins.gerrit.trigger.hudsontrigger.data.Branch' {
                                compareType("PLAIN")
                                pattern("master")
                            }
                        }
                    }
                }
                gerritxml / serverName("ADOP Gerrit")
            }
        }
    }
    steps{
        shell('''set +x
|git clone --bare  https://github.com/adm3942soit/Master.git
|[[ -s '/usr/local/lib/rvm' ]] && source '/usr/local/lib/rvm\'
|cd $WORKSPACE
|ls
|cd $WORKSPACE/Master.git
|ls
|git remote set-url --add origin ssh://jenkins@gerrit:29418/Master.git
|#ssh -p 29418 jenkins@gerrit gerrit create-project -n Master
|git config credential.helper store
|git config --global push.default simple
|git remote add --mirror=push github ssh://jenkins@gerrit:29418/Master.git
|git remote -v
|git push ssh://jenkins@gerrit:29418/Master.git
|git clone ssh://jenkins@gerrit:29418/Master.git HEAD:refs/for/master
|git branch -r
|set -x'''.stripMargin())
    }
}
queue(nameJob)

