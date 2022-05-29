package com.degenerator.hackinhome.service.student;

import com.degenerator.hackinhome.dao.student.StudentRepository;
import com.degenerator.hackinhome.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDto> getList() {
        return studentRepository.findAll();
    }

    @Override
    public void save(StudentDto dto) {
        studentRepository.save(dto);
    }

    @Override
    public List<StudentDto> getByGroup(String groupName) {
        return studentRepository.findByGroupName(groupName);
    }
}
