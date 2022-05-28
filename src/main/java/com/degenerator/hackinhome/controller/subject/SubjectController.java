package com.degenerator.hackinhome.controller.subject;

import com.degenerator.hackinhome.dto.SubjectDto;
import com.degenerator.hackinhome.service.subjects.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubjectController {
    @Autowired
    private ISubjectService subjectService;

    @GetMapping("/list")
    public List<SubjectDto> list() {
        return subjectService.getList();
    }

    @PostMapping("/save")
    public void save(@RequestBody SubjectDto dto) {
        subjectService.save(dto);
    }
}
