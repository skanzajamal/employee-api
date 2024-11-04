package com.dto;

import com.model.EmployeeEntity;

import java.util.List;
import java.util.stream.Collectors;

public class DtoConvertor {

    public static EmployeeDto toDto(EmployeeEntity entity) {

        EmployeeDto dto = new EmployeeDto();
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setJobTitle(entity.getJobTitle());
        dto.setDepartment(entity.getDepartment());
        dto.setBirthDay(entity.getBirthDay());
        dto.setHobbies(entity.getHobbies());
        return dto;
    }

    public static List<EmployeeDto> toDtoList(List<EmployeeEntity> entities) {
        return entities.stream().map(DtoConvertor::toDto).collect(Collectors.toList());
    }

} //ENDCLASS
