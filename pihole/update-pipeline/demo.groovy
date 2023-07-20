pipeline {
    agent {
    node {
        label 'Prod-1'
    }
}
    stages {
        stage('Stoping and Removing the Container') {
            steps {
                sh 'docker stop Pihole-Primary'
                sh 'docker rm Pihole-Primary'
            }
        }

        stage('Removing Docker Image') {
            steps {
                sh 'docker rmi pihole/pihole:latest'
            }
        }

        stage('Downloading latest Docker Image') {
            steps {
                sh 'docker pull pihole/pihole:latest'
            }
        }

        stage('Launching the latest image') {
            steps {
                sh ' ur run command'
            }
        }
    }

    post {
        always {
            echo 'Pihole Updated.'
        }
    }
}
