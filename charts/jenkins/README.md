# Jenkins Helm Chart

A Helm chart for deploying a Jenkins master and dynamically-scaling slave cluster via the [Kubernetes plugin](https://github.com/jenkinsci/kubernetes-plugin)

| Chart version | Kubernetes version |
| --- | --- |
| `0.9.3` | At least `1.9.x` and `1.10.x` |
| `1.0.0` | At least `1.11.x` |

## Features

- Uses the [Configuration as Code plugin](https://github.com/jenkinsci/configuration-as-code-plugin) for declarative configurion of the Jenkins master
- Has custom CSS via the [simple-theme-plugin](https://github.com/jenkinsci/simple-theme-plugin) for better typography and colors in Jenkins UI
- Currently tied to using *Bitbucket Server*

## Configuring the chart

See `values.yaml` file for the configuration schema.
