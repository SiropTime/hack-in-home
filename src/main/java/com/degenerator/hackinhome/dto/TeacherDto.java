package com.degenerator.hackinhome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class TeacherDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String email;
    private String phone;
    private String authorUrlProfile;
    @OneToOne(targetEntity = DepartmentDto.class)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private DepartmentDto departmentId;
}
