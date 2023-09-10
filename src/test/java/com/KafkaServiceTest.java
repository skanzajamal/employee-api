package com;

import com.constants.AppConstants;
import com.model.EmployeeEntity;
import com.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@TestPropertySource(
        properties = {
                "spring.kafka.consumer.auto-offset-reset=earliest",
                "jdbc:postgresql://localhost:5432/postgres",
        }
)
class KafkaServiceTest extends EmployeeApplicationTest {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    UUID uuid = UUID.randomUUID();
    Date dateOfBirth = new Date();

    @Test
    void shouldHandleEmployeeCreateEvents() {

        // Create event
        kafkaTemplate.send(AppConstants.TOPIC_NAME, "employee has been created");

        EmployeeEntity employeeEntity = new EmployeeEntity(uuid, "Anton Adam", "anton_adam@gmail.com", dateOfBirth, "travelling");
        var record = employeeRepository.save(employeeEntity);

        Awaitility.await()
                .pollInterval(Duration.ofSeconds(3))
                .atMost(10, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(record.getId());
                    Assertions.assertThat(optionalEmployee).isPresent();
                    Assertions.assertThat(optionalEmployee.get().getFullName()).isEqualTo("Anton Adam");
                    Assertions.assertThat(optionalEmployee.get().getEmail()).isEqualTo("anton_adam@gmail.com");
                });

    }

} //ENDCLASS
