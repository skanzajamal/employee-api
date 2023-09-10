package com.dto;

import com.model.EmployeeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class DtoConvertor {

    @Autowired
    private ModelMapper modelMapper;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public EmployeeDto toDto(EmployeeEntity entity) {
        return modelMapper.map(entity, EmployeeDto.class);
    }

} //ENDCLASS
