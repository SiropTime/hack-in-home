package com.degenerator.hackinhome.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "students")
public class StudentDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String middlename;
    private String surname;
    private String email;
    private String additionalInfo;
    private boolean isHeadman;
    @ManyToOne(targetEntity = GroupDto.class)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private GroupDto groupId;
    @OneToMany(targetEntity = SubjectDto.class)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private List<SubjectDto> subjects;
}
