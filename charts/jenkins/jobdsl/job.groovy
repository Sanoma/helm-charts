multibranchPipelineJob(jobProperties.bitbucketRepo.repository) {
  displayName(jobProperties.bitbucketRepo.repoOwner + "/" + jobProperties.bitbucketRepo.repository)
  branchSources {
    branchSource {
      source {
        bitbucket {
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
  configure {
    def traits = it / sources / data / 'jenkins.branch.BranchSource' / source / traits
    traits << 'com.cloudbees.jenkins.plugins.bitbucket.BranchDiscoveryTrait' {
      strategyId(3)
    }
  }
}
