package com;

import com.model.EmployeeEntity;
import com.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class DataModelServiceTest extends EmployeeApplicationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    UUID uuid = UUID.randomUUID();
    Date dateOfBirth = new Date();

    @Test
    public void testSaveEmployee() {
        EmployeeEntity employeeEntity = new EmployeeEntity(uuid, "Daniyal Akhtar", "daniyal_akhtar@gmail.com", dateOfBirth, "flying drones");
        var record = employeeRepository.save(employeeEntity);
        Assertions.assertThat(record).isNotNull();
    }

    @Test
    public void testEmployeeById() {
        employeeRepository.findById(uuid).ifPresent(record -> Assertions.assertThat(record.getFullName()).isEqualTo("Daniyal Akhtar"));
    }

    @Test
    public void testListEmployess() {
        List<EmployeeEntity> record = employeeRepository.findAll();
        Assertions.assertThat(record).size().isGreaterThan(0);
    }

    @Test
    public void testUpdateEmployee() {

        Optional<EmployeeEntity> record = employeeRepository.findById(uuid);
        if(record.isPresent()) {
            var updatedData = record.get();
            updatedData.setHobbies("playing football");
            employeeRepository.save(updatedData);
            employeeRepository.findById(uuid).ifPresent(updatedRecord -> Assertions.assertThat(updatedRecord.getHobbies()).isEqualTo("playing football"));
        }
    }

    @Test
    public void testDeleteEmployee() {

        Optional<EmployeeEntity> record = employeeRepository.findById(uuid);
        if(record.isPresent()) {
            employeeRepository.deleteById(record.get().getId());
            Mockito.verify(employeeRepository).deleteById(uuid);
        }
    }

} //ENDCLASS
