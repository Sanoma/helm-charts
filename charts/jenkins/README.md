# Jenkins Helm Chart

A Helm chart for deploying a Jenkins master and dynamically-scaling slave cluster via the [Kubernetes plugin](https://github.com/jenkinsci/kubernetes-plugin)

| Chart version | Kubernetes version |
| --- | --- |
| `0.9.3` | `1.9.x` and `1.10.x`, possibly `1.8.x` and lower |
| `>=1.0.0` | `1.11.x`+, tested with `1.11.x` and `1.16.x` |

## Features

- Uses the [Configuration as Code plugin](https://github.com/jenkinsci/configuration-as-code-plugin) for declarative configurion of the Jenkins master
- Has custom CSS via the [simple-theme-plugin](https://github.com/jenkinsci/simple-theme-plugin) for better typography and colors in Jenkins UI
- Currently tied to using *Bitbucket Server*


## Required jenkins plugins

The Helm chart assumes your Jenkins Docker image has these plugins installed:

```
configuration-as-code:1.36
configuration-as-code-support:1.18
kubernetes:1.19.3
job-dsl:1.76
git:4.2.0
cloudbees-bitbucket-branch-source:2.7.0
pipeline-build-step:2.11
workflow-multibranch:2.21
workflow-aggregator:2.6
workflow-job:2.36
ansicolor:0.6.3
simple-theme-plugin:0.6
basic-branch-build-strategies:1.3.2
```

## Configuring the chart

See `values.yaml` file for the configuration schema.
