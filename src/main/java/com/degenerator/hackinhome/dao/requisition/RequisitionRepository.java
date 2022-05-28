package com.degenerator.hackinhome.dao.requisition;

import com.degenerator.hackinhome.dto.RequisitionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisitionRepository extends JpaRepository<RequisitionDto, Integer> {
}
