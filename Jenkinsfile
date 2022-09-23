pipeline{
    agent {
        label 'node-awsec2-docker'
    }
    environment {
        gitcommit = "${gitcommit}"
    }
    tools{
        maven 'maven-jenkins'
    }
    stages{
        stage('Verificación SCM') {
          steps {
            script {
              sh "git rev-parse --short HEAD > .git/commit-id"
              gitcommit = readFile('.git/commit-id').trim()
            }
          }
        }
        stage("Build"){
            steps{
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage("Test"){
            steps{
                sh 'mvn test'
            }
        }
        stage('Docker Build & Push') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub') {
                        def image = docker.build("plchavez98/tds-microservice-products:${gitcommit}", ".")
                        image.push()
                    }
                }
            }
        }
    }
}