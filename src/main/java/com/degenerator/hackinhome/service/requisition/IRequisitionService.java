package com.degenerator.hackinhome.service.requisition;

import com.degenerator.hackinhome.dto.RequisitionDto;

import java.util.List;

public interface IRequisitionService {
    void save(RequisitionDto dto);
    List<RequisitionDto> getList();
    RequisitionDto find(Integer id);
}
