docker exec -it broker-0 /bin/kafka-topics --list --bootstrap-server broker-0:9080
docker exec -it broker-1 /bin/kafka-topics --list --bootstrap-server broker-1:9081
docker exec -it broker-2 /bin/kafka-topics --list --bootstrap-server broker-2:9082
pause