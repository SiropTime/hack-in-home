package com.degenerator.hackinhome.dao.student;

import com.degenerator.hackinhome.dto.StudentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentDto, Integer> {

    @Query(value = "select * from students s" +
            "join groups g on s.group_id = g.id" +
            "where g.name = :groupName", nativeQuery = true)
    List<StudentDto> findByGroupName(@Param("groupName") String groupName);
}
