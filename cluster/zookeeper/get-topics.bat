docker exec -it broker-0 /bin/kafka-topics --list --bootstrap-server broker-0:9091
docker exec -it broker-1 /bin/kafka-topics --list --bootstrap-server broker-1:9093
docker exec -it broker-2 /bin/kafka-topics --list --bootstrap-server broker-2:9095
pause