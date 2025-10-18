docker exec -it broker-0 /bin/kafka-topics --create --topic organization --bootstrap-server localhost:9080 --partitions 1 --replication-factor 1
pause