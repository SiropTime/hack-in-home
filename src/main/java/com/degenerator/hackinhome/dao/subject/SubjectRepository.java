package com.degenerator.hackinhome.dao.subject;

import com.degenerator.hackinhome.dto.SubjectDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectDto, Integer> {
}
