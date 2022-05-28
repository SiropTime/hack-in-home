package com.degenerator.hackinhome.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "subjects")
public class SubjectDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String url;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date moduleStartDate;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date moduleEndDate;
    @OneToOne(targetEntity = TeacherDto.class)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private TeacherDto teacherId;
}
