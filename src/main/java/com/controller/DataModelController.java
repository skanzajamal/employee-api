package com.controller;

import com.model.EmployeeEntity;
import com.service.DataModelService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employee")
public class DataModelController {

    private final DataModelService dataModelService;

    public DataModelController(DataModelService dataModelService) {
        this.dataModelService = dataModelService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeEntity addEmployee(@RequestBody EmployeeEntity employeeEntity) {
        return dataModelService.createEmployee(employeeEntity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeEntity updateEmployee(@RequestBody EmployeeEntity employeeEntity, @PathVariable("id") UUID id) {
        return dataModelService.updateEmployee(employeeEntity, id);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeEntity getById(@PathVariable("id") UUID id) {
        return dataModelService.getEmployeeById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") UUID id) {
        dataModelService.deleteEmployeeById(id);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeEntity> getAllEmployees() {
        return dataModelService.listOfAllEmployees();
    }

} //ENDCLASS