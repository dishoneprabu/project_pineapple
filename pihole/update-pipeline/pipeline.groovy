// Author : Dishone Prabu
// Targer nodes by mentioning the node label name
pipeline {
    agent {
    node {
        label 'Prod-1'
    }
}

// Stage 1
    stages {
        stage('Stoping and Removing the Container') {
            steps {
                sh 'docker stop Pihole'
                sh 'docker rm Pihole'
            }
        }

// Stage 2
        stage('Removing Docker Image') {
            steps {
                sh 'docker rmi pihole/pihole:latest'
            }
        }

// Stage 3
        stage('Downloading latest Docker Image') {
            steps {
                sh 'docker pull pihole/pihole:latest'
            }
        }

// Stage 4
        stage('Launching the latest image') {
            steps {
                sh 'docker run -d --name=Pihole -h pihole -e TZ=Asia/Kolkata -v /pihole/etc-pihole:/etc/pihole -v /pihole/etc-dnsmasq.d:/etc/dnsmasq.d --cap-add=NET_ADMIN pihole/pihole:latest'
            }
        }
    }

    post {
        always {
            echo 'Pihole Updated.'
        }
    }
}