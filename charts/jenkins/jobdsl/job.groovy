multibranchPipelineJob(jobProperties.bitbucketRepo.repository) {
  displayName(jobProperties.bitbucketRepo.repoOwner + "/" + jobProperties.bitbucketRepo.repository)
  branchSources {
    branchSource {
      source {
        bitbucket {
          id (jobProperties.bitbucketRepo.repository)
          serverUrl(jobProperties.bitbucketRepo.serverUrl)
          repoOwner(jobProperties.bitbucketRepo.repoOwner)
          repository(jobProperties.bitbucketRepo.repository)
          credentialsId(jobProperties.bitbucketRepo.credentialsId)
          traits {
            sshCheckoutTrait {
              credentialsId(jobProperties.gitRepo.credentialsId)
            }
            localBranchTrait()
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
    discardOldItems {}
  }
  configure {
    def traits = it / sources / data / 'jenkins.branch.BranchSource' / source / traits
    traits << 'com.cloudbees.jenkins.plugins.bitbucket.BranchDiscoveryTrait' {
      strategyId(3)
    }
  }
  configure {
    it / triggers / 'com.cloudbees.hudson.plugins.folder.computed.PeriodicFolderTrigger' {
      spec('* * * * *')
      interval('60000')
    }
  }
}
