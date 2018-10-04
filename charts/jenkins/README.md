# Jenkins Helm Chart

Jenkins master and dynamically-scaling slave cluster via the [Kuberntes plugin](https://github.com/jenkinsci/kubernetes-plugin)

## Features

- Uses the [Configuration as Code plugin](https://github.com/jenkinsci/configuration-as-code-plugin) for declarative configurion of the Jenkins master
- Has custom CSS via the [simple-theme-plugin](https://github.com/jenkinsci/simple-theme-plugin) for better typography and colors in Jenkins UI
- Currently tied to using *Bitbucket Server* for generating multibranch pipeline job of either `simple` or `monorepo` type.

### Generating monorepo multibranch pipeline jobs

```yaml
jobGenerator:
- type: monorepo
  properties:
    branchFilterExcludes: feature* hotfix* fix*
    branchFilterIncludes: ""
    bitbucketRepo:
      serverUrl: https://private.bitbucket.io
      repoOwner: monorepo-project
      repository: monorepo
      credentialsId: bitbucket-userpass
    gitRepo:
      url: ssh://git@private.bitbucket.io:7999/monorepo-project/monorepo.git
      credentialsId: bitbucket-repo-ssh
    jobPaths:
    - group-a/component-1
    - group-a/component-2
    - group-b/component-3
    - group-b/component-4
```

The `jobPaths` variable specifies the paths in the monorepo that contain a Jenkinsfile to be discovered.

### Generating simple multibranch pipeline jobs

```yaml
jobGenerator:
- type: simple
  properties:
    branchFilterExcludes: master production
    branchFilterIncludes: ""
    bitbucketRepo:
      serverUrl: https://private.bitbucket.io
      repoOwner: simple-project
      repository: repo
      credentialsId: bitbucket-userpass
```

## Configuring the chart

See `values.yaml` file for the configuration schema.
