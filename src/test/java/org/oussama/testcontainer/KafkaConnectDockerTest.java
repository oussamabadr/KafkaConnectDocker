package org.oussama.testcontainer;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.containers.wait.strategy.WaitStrategy;

import java.io.File;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaConnectDockerTest {

    private static final WaitStrategy waitStrategy = Wait.forHealthcheck().withStartupTimeout(Duration.ofMinutes(2));

    private HttpResponse<?> response;

    @Test
    void shouldReturnOKWhenQueryKafkaConnectRestApi() throws UnirestException {
        // Given
        DockerComposeContainer dockerComposeContainer = new DockerComposeContainer(new File("src/main/resources/docker-compose.yml")) //
                                                                    .waitingFor("connect", waitStrategy);
        try (dockerComposeContainer) {
            dockerComposeContainer.start();

        // When
            response = Unirest.get("http://localhost:8083/connectors/").asJson();
        }

        // Then
        assertThat( response.getStatus() ).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    void shouldFailWhenQueryWrongPathOfKafkaConnectRestApi() throws UnirestException {
        // Given
        DockerComposeContainer dockerComposeContainer = new DockerComposeContainer(new File("src/main/resources/docker-compose.yml")) //
                                                                    .waitingFor("connect", waitStrategy);

        try (dockerComposeContainer) {
            dockerComposeContainer.start();

        // When
            response = Unirest.get("http://localhost:8083/NONEXISTENT_PATH/").asJson();
        }

        // Then
        assertThat( response.getStatus() ).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

}