package com.degenerator.hackinhome.service.subjects;

import com.degenerator.hackinhome.dto.SubjectDto;

import java.util.List;

public interface ISubjectService {
    List<SubjectDto> getList();
    void save(SubjectDto dto);
}
