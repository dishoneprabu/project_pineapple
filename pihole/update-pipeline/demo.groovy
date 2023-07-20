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
                sh 'docker run -d --name=Pihole-Primary -h pihole.j2dk.in --net=Home --ip 192.168.1.6 -e TZ=Asia/Kolkata -v /mnt/disk_01/docker/volume/pihole/etc-pihole:/etc/pihole -v /mnt/disk_01/docker/volume/pihole/etc-dnsmasq.d:/etc/dnsmasq.d --cap-add=NET_ADMIN --restart unless-stopped pihole/pihole:latest'
            }
        }
    }

    post {
        always {
            echo 'Pihole Updated.'
        }
    }
}