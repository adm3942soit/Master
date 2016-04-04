job('Test1 create Job'){
    scm{
        git('https://github.com/adm3942soit/Master.git')
    }
    steps{
        maven{
            mavenInstallation("maven")
            goals("clean package")
        }
        maven{
            mavenInstallation("maven")
            goals("deploy")
        }

    }
}