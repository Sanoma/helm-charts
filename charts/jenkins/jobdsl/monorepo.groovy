// Create a Jenkins jobs for a complex monorepo
// Create the main job that will trigger component-specific jobs
multibranchPipelineJob(jobProperties.bitbucketRepo.repository) {
    branchSources {
        branchSource {
            source {
                bitbucket {
                    serverUrl(jobProperties.bitbucketRepo.serverUrl)
                    repoOwner(jobProperties.bitbucketRepo.repoOwner)
                    repository(jobProperties.bitbucketRepo.repository)
                    credentialsId(jobProperties.bitbucketRepo.credentialsId)
                    traits {
                        headWildcardFilter {
                            includes(jobProperties.branchFilterIncludes)
                            excludes(jobProperties.branchFilterExcludes)
                        }
                    }
                }
            }
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(30)
        }
    }
    configure {
        it / triggers / 'com.cloudbees.hudson.plugins.folder.computed.PeriodicFolderTrigger' {
            spec('* * * * *')
            interval('60000')
        }
    }
    configure {
        def traits = it / sources / data / 'jenkins.branch.BranchSource' / source / traits
        traits << 'com.cloudbees.jenkins.plugins.bitbucket.BranchDiscoveryTrait' {
           strategyId(3) // detect all branches
        }
    }
}

// Create jobs for components listed in `jobPaths` variable
for (jobPath in jobProperties.jobPaths) {
    def directoryList = jobPath.split("/").init()
    def n = directoryList.size()
    def i = 0

    while (i < n) {
      folder(directoryList.take(i + 1).join("/")) {
        description("https://source.sanoma.com/projects/SMFDP/repos/smf-dp/browse/${directoryList.take(i + 1).join("/")}")
      }
      i = i + 1
    }

    // Create a job in that folder
    multibranchPipelineJob(jobPath) {
        factory {
            workflowBranchProjectFactory {
                scriptPath("${jobPath}/Jenkinsfile")
            }
        }
        branchSources {
            branchSource {
                source {
                    git {
                        id('source-unique-id')
                        remote(jobProperties.gitRepo.url)
                        credentialsId(jobProperties.gitRepo.credentialsId)
                        traits {
                            headWildcardFilter {
                                includes(jobProperties.branchFilterIncludes)
                                excludes(jobProperties.branchFilterExcludes)
                            }
                        }
                    }
                }
                strategy {
                    defaultBranchPropertyStrategy {
                        props {
                            noTriggerBranchProperty()
                        }
                    }
                }
            }

        }
        orphanedItemStrategy {
            discardOldItems {
                numToKeep(30)
            }
        }
        configure {
            def traits = it / sources / data / 'jenkins.branch.BranchSource' / source / traits
            traits << 'jenkins.plugins.git.traits.BranchDiscoveryTrait' {}
        }
        configure {
            it / triggers / 'com.cloudbees.hudson.plugins.folder.computed.PeriodicFolderTrigger' {
                spec('* * * * *')
                interval('60000')
            }
        }
    }
}
