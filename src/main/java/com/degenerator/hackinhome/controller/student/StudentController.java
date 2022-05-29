package com.degenerator.hackinhome.controller.student;

import com.degenerator.hackinhome.dto.StudentDto;
import com.degenerator.hackinhome.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/list")
    public List<StudentDto> list(){
        return studentService.getList();
    }

    @GetMapping("/group")
    public List<StudentDto> listByGroupName(@RequestParam String groupName) {
        return studentService.getByGroup(groupName);
    }

    @PostMapping("/save")
    public void save (@RequestBody StudentDto dto) {
        studentService.save(dto);
    }
}
