package com.degenerator.hackinhome.service.subjects;

import com.degenerator.hackinhome.dao.subject.SubjectRepository;
import com.degenerator.hackinhome.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService implements ISubjectService {
    @Autowired
    private SubjectRepository repository;

    @Override
    public List<SubjectDto> getList() {
        return repository.findAll();
    }

    @Override
    public void save(SubjectDto dto) {
        repository.save(dto);
    }
}
