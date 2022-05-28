package com.degenerator.hackinhome.dao.department;

import com.degenerator.hackinhome.dto.DepartmentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentDto, Integer> {
}
