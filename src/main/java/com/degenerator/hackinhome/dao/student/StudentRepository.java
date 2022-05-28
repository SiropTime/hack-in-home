package com.degenerator.hackinhome.dao.student;

import com.degenerator.hackinhome.dto.StudentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentDto, Integer> {
}
