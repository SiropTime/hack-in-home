package com.degenerator.hackinhome.dto;

import com.degenerator.hackinhome.enums.RequisitionType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "requisitions")
public class RequisitionDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private RequisitionType type;
    private String additionalInfo;
    @OneToOne(targetEntity = StudentDto.class)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentDto studentId;
}
