// Folders
def projectFolderName = "${PROJECT_NAME}"

// Jobs
def masterPipelineView = buildPipelineView(projectFolderName + "/Master")

// Setup FestivalPortal Pipeline
masterPipelineView.with{
    title('Master Pipeline')
    displayedBuilds(5)
    selectedJob("Master_build")
    showPipelineParameters()
    showPipelineDefinitionHeader()
    refreshFrequency(5)
}