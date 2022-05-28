package com.degenerator.hackinhome.service.requisition;

import com.degenerator.hackinhome.dao.requisition.RequisitionRepository;
import com.degenerator.hackinhome.dto.RequisitionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequisitionService implements IRequisitionService{

    @Autowired
    private RequisitionRepository repository;

    @Override
    public void save(RequisitionDto dto) {
        repository.save(dto);
    }

    @Override
    public List<RequisitionDto> getList() {
        return repository.findAll();
    }

    @Override
    public RequisitionDto find(Integer id) {
        return repository.getById(id);
    }
}
