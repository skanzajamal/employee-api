package com.model;

import com.enumeration.Department;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class EmployeeEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false, length = 64)
    private String fullName;

    @Column(nullable = false, unique = true, length = 64)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column
    private Department department;

    @Column(nullable = false)
    private String jobTitle;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(nullable = false)
    private Date birthDay;

    @Column(length = 2048)
    private String hobbies;

} //ENDCLASS
