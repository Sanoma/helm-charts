# Jenkins Helm Chart

Jenkins master and dynamically-scaling slave cluster via the [Kuberntes plugin](https://github.com/jenkinsci/kubernetes-plugin).

## Features

- Uses the [Configuration as Code plugin](https://github.com/jenkinsci/configuration-as-code-plugin) for declarative configurion of the Jenkins master
- Uses a [Job DSL](https://github.com/jenkinsci/job-dsl-plugin) Groovy script (in `jobdsl/template.groovy`) for creating a set of Jenkins jobs from a Bitbucket Server monorepo
- Has custom CSS via the [simple-theme-plugin](https://github.com/jenkinsci/simple-theme-plugin) for better typography and colors in Jenkins UI

## Configuring the chart

See `values.yaml` file for the configuration schema.
