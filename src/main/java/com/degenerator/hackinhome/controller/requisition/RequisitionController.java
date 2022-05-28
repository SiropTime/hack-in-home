package com.degenerator.hackinhome.controller.requisition;

import com.degenerator.hackinhome.dto.RequisitionDto;
import com.degenerator.hackinhome.service.requisition.IRequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requisition")
public class RequisitionController {

    @Autowired
    private IRequisitionService requisitionService;

    @GetMapping("/list")
    public List<RequisitionDto> list() {
        return requisitionService.getList();
    }

    @GetMapping("/{id}")
    public RequisitionDto getById(@PathVariable Integer id) {
        return requisitionService.find(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody RequisitionDto dto) {
        requisitionService.save(dto);
    }
}
