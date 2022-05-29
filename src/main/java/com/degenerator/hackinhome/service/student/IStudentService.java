package com.degenerator.hackinhome.service.student;

import com.degenerator.hackinhome.dto.StudentDto;

import java.util.List;

public interface IStudentService {
    List<StudentDto> getList();
    void save(StudentDto dto);
    List<StudentDto> getByGroup(String groupName);
}
