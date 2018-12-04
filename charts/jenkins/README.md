# Jenkins Helm Chart

Jenkins master and dynamically-scaling slave cluster via the [Kuberntes plugin](https://github.com/jenkinsci/kubernetes-plugin)

## Features

- Uses the [Configuration as Code plugin](https://github.com/jenkinsci/configuration-as-code-plugin) for declarative configurion of the Jenkins master
- Has custom CSS via the [simple-theme-plugin](https://github.com/jenkinsci/simple-theme-plugin) for better typography and colors in Jenkins UI
- Currently tied to using *Bitbucket Server*

## Configuring the chart

See `values.yaml` file for the configuration schema.
