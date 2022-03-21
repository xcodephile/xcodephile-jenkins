pipeline {
    agent any

    environment {
        NAME = 'Rifqi'
        COUNTRY = 'ID'

        SECRET = credentials('REDIS_PASSWORD')
    }

    stages{
        stage('Build'){
            steps {
                sh "echo 'Building...'"
                sh "echo $SECRET"
            }
        }
        stage('Testing'){
            steps {
                sh "echo 'Testing...'"
            }
        }
        stage('Deploying'){
            steps {
                retry(3){
                    sh "echo 'Deploying...'"
                }
                timeout(time: 3, unit: 'SECONDS'){
                    sh "echo 'Done.'"
                }
            }
        }
    }
    post {
        always {
            echo "I will always get executed"
        }
        success {
            echo "I will only get executed if succeded"
        }
        failure {
            echo "I will only get executed if failed"
        }
        unstable {
            echo "I will only get executed if unstable"
        }
    }
}