
jenkins.model.Jenkins.theInstance.getProjects().each { job ->
    if (job.name != 'bootstraps' && !job.name.contains('Jenkins')) {
        job.delete()
    }
}

jenkins.model.Jenkins.theInstance.getViews().each {
    view ->
        if (view.name != 'All' && view.name != 'Jenkins') {
            jenkins.model.Jenkins.theInstance.deleteView(view)
        }
}

job('01-clone') {
    deliveryPipelineConfiguration("Build", "clone")
    publishers {
        publishCloneWorkspace '**', '', 'Any', 'TAR', true, null
        downstream '02-compile', 'SUCCESS'
    }
}

job('02-compile') {
    deliveryPipelineConfiguration("Compile", "compile")
    publishers {
        publishCloneWorkspace '**', '', 'Any', 'TAR', true, null
        downstream '03-package', 'SUCCESS'
    }
}

job('03-package') {
    deliveryPipelineConfiguration("Package", "package")
    publishers {
        publishCloneWorkspace '**', '', 'Any', 'TAR', true, null
        downstream '04-test', 'SUCCESS'
    }
}

job('04-test') {
    deliveryPipelineConfiguration("Test", "test")
    publishers {
        publishCloneWorkspace '**', '', 'Any', 'TAR', true, null
        downstream '05-release', 'SUCCESS'
    }
}

job('05-release') {
    deliveryPipelineConfiguration("Release", "release")
    publishers {
        publishCloneWorkspace '**', '', 'Any', 'TAR', true, null
    }
}

deliveryPipelineView('Delivery pipeline') {
    pipelines {
        component('Delivery pipeline', '01-clone')
    }
}

buildPipelineView('Build pipeline') {
    title 'Build pipeline'
    selectedJob '01-clone'
}
