package com.degenerator.hackinhome.dao.group;

import com.degenerator.hackinhome.dto.GroupDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupDto, Integer> {
}
