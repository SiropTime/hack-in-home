package com.degenerator.hackinhome.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "groups")
public class GroupDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    @OneToOne(targetEntity = DepartmentDto.class)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private DepartmentDto departmentId;
}
