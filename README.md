# Simple Test of Kafka Connect using TestContainer


+ JDK 11.
+ You need to install [docker](https://docs.docker.com/get-docker/) & [docker-compose](https://docs.docker.com/compose/install/). 
+ Docker file was picked from [kafka-connect-datagen](https://github.com/confluentinc/kafka-connect-datagen/blob/301fa03e6675f56ebcfbfd55770588eb6aed312f/docker-compose.yml).

+ In test, You may consider using [Junit 5 Extension Model](https://junit.org/junit5/docs/current/user-guide/#extensions-overview) to avoid starting containers for each test.

 