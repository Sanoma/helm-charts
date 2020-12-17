for (suffix in jobProperties.jobNameSuffixes) {
  multibranchPipelineJob(jobProperties.githubRepo.repository + suffix) {
    displayName(jobProperties.githubRepo.repoOwner + '/' + jobProperties.githubRepo.repository + suffix)
    branchSources {
      branchSource {
        if (jobProperties.isTrunkBranch) {
          strategy {
            allBranchesSame {
              props {
                suppressAutomaticTriggering()
              }
            }
          }
        }
        buildStrategies {
          buildNamedBranches {
            filters {
              regex {
                regex(jobProperties.headRegexFilter)
                caseSensitive(true)
              }
            }
          }
        }
        source {
          github {
            id(jobProperties.githubRepo.repository)
            repoOwner(jobProperties.githubRepo.repoOwner)
            repository(jobProperties.githubRepo.repository)
            repositoryUrl(jobProperties.githubRepo.repositoryUrl)
            configuredByUrl(true)
            credentialsId(jobProperties.githubRepo.githubAppCredentialsId)
            traits {
              localBranchTrait()
              disableStatusUpdateTrait()
              headRegexFilter { regex(jobProperties.headRegexFilter) }
              gitHubBranchDiscovery { strategyId(3) }
              if (jobProperties.isTrunkBranch) {
                pruneStaleBranchTrait()
              }
            }
          }
        }
      }
    }
    if (jobProperties.isTrunkBranch) {
      orphanedItemStrategy {
        discardOldItems {
          daysToKeep(14)
        }
      }
    }
  }
}
