package com.degenerator.hackinhome.dao.teacher;

import com.degenerator.hackinhome.dto.TeacherDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherDto, Integer> {
}
