// Create a jenkins job for a simple repo with one Jenkinsfile in repo root
multibranchPipelineJob(jobProperties.bitbucketRepo.repository) {
  branchSources {
    displayName(jobProperties.bitbucketRepo.repoOwner + "/" + jobProperties.bitbucketRepo.repository)
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
  // orphanedItemStrategy {
  //   discardOldItems {
  //     numToKeep(30)
  //   }
  // }
  configure {
    it / triggers / 'com.cloudbees.hudson.plugins.folder.computed.PeriodicFolderTrigger' {
      spec(jobProperties.periodicFolderTrigger.spec)
      interval(jobProperties.periodicFolderTrigger.interval)
    }
  }
  configure {
    def traits = it / sources / data / 'jenkins.branch.BranchSource' / source / traits
    traits << 'com.cloudbees.jenkins.plugins.bitbucket.BranchDiscoveryTrait' {
      strategyId(3) // detect all branches
    }
  }
}
