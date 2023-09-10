package com.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class EmployeeDto {

    @JsonDeserialize
    private UUID id;

    private String fullName;

    private String email;

    private Date birthDay;

    private String hobbies;

} //ENDCLASS
