# Pipeline Code for Pihole Container Update

## Target
###### In this code, Enter the node label name in the label section, it will target the pipeline to the targeted node
```
pipeline {
    agent {
    node {
        label 'Prod-1'
    }
}
```

## Stages

#### Stage 1
###### Change the container name in the steps based on your pihole container name
```
// Stage 1
    stages {
        stage('Stoping and Removing the Container') {
            steps {
                sh 'docker stop Pihole'
                sh 'docker rm Pihole'
            }
        }
```

#### Stage 2
###### Here mention the Docker image name which you are currently running to remove the old image
```
// Stage 2
        stage('Removing Docker Image') {
            steps {
                sh 'docker rmi pihole/pihole:latest'
            }
        }
```

#### Stage 3
###### If you want the latest image, Then you dont need to change the code here.
###### If you need to change the version, mention the version by replacing the latest tag. 
```
// Stage 3
        stage('Downloading latest Docker Image') {
            steps {
                sh 'docker pull pihole/pihole:latest'
            }
        }
```

#### Stage 4
###### Here mention your docker run command
```
// Stage 4
        stage('Launching the latest image') {
            steps {
                sh 'docker run -d --name=Pihole -h pihole -e TZ=Asia/Kolkata -v /pihole/etc-pihole:/etc/pihole -v /pihole/etc-dnsmasq.d:/etc/dnsmasq.d --cap-add=NET_ADMIN pihole/pihole:latest'
            }
        }
    }
```
##### After editing the code, copy the code to your jenkins server and Enjoy!!!.