package com.degenerator.hackinhome.controller.faq;

import com.degenerator.hackinhome.dto.FaqDto;
import com.degenerator.hackinhome.service.faq.IFaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faq")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FaqController {

    @Autowired
    private IFaqService faqService;

    @GetMapping("/all")
    public List<FaqDto> getAll() {
        return faqService.getAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody FaqDto dto) {
        faqService.save(dto);
    }

    @PostMapping("/{id}")
    public void edit(@RequestBody FaqDto dto){
        faqService.update(dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@RequestBody FaqDto dto){
        faqService.delete(dto);
    }
}
