package com.service;

import com.constants.AppConstants;
import com.kafka.config.KafkaProducer;
import com.model.EmployeeEntity;
import com.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DataModelService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final KafkaProducer kafkaProducer;

    public DataModelService(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public EmployeeEntity createEmployee(EmployeeEntity employeeEntity) {
        var record = employeeRepository.save(employeeEntity);
        kafkaProducer.sendMessage("employee " + record.getFullName() + " has been created");
        return record;
    }

    public EmployeeEntity updateEmployee(EmployeeEntity personEntity, UUID id) {
        var record = employeeRepository.getOne(id);
        BeanUtils.copyProperties(personEntity, record);
        var result = employeeRepository.save(record);
        kafkaProducer.sendMessage("employee " + result.getFullName() + " has been updated");
        return result;
    }

    public EmployeeEntity getEmployeeById(UUID id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public void deleteEmployeeById(UUID id) {
        employeeRepository.deleteById(id);
        kafkaProducer.sendMessage("employee with id: " + id + " has been deleted");
    }

    public List<EmployeeEntity> listOfAllEmployees() {
        return employeeRepository.findAll();
    }

} //ENDCLASS