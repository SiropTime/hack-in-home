package com.degenerator.hackinhome.dao.faq;

import com.degenerator.hackinhome.dto.FaqDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<FaqDto, Integer> {
}
