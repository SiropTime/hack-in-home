package com.degenerator.hackinhome.service.requisition;

import com.degenerator.hackinhome.dao.requisition.RequisitionRepository;
import com.degenerator.hackinhome.dao.student.StudentRepository;
import com.degenerator.hackinhome.dto.RequisitionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequisitionService implements IRequisitionService{

    @Autowired
    private RequisitionRepository requisitionRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void save(RequisitionDto dto) {
        /*mock*/
        if (dto.getStudentId() == null) {
            dto.setStudentId(studentRepository.getById(3));
        }
        requisitionRepository.save(dto);
    }

    @Override
    public List<RequisitionDto> getList() {
        return requisitionRepository.findAll();
    }

    @Override
    public RequisitionDto find(Integer id) {
        return requisitionRepository.getById(id);
    }
}
