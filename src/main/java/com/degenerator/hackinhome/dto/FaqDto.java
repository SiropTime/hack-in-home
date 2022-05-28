package com.degenerator.hackinhome.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "faq")
public class FaqDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private String answer;
}
