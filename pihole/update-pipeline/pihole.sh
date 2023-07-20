#! /bin/bash
docker stop Pihole-Primary
docker rm Pihole-Primary
docker rmi pihole/pihole:latest
docker pull pihole/pihole:latest
docker run -d --name=Pihole-Primary -h pihole.j2dk.in --net=Home --ip 192.168.1.6 -e TZ=Asia/Kolkata -v /mnt/disk_01/docker/volume/pihole/etc-pihole:/etc/pihole -v /mnt/disk_01/docker/volume/pihole/etc-dnsmasq.d:/etc/dnsmasq.d --cap-add=NET_ADMIN --restart unless-stopped pihole/pihole:latest
echo 'Pihole Updated.'