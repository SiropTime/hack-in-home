package com.degenerator.hackinhome.service.faq;

import com.degenerator.hackinhome.dto.FaqDto;

import java.util.List;

public interface IFaqService {
    List<FaqDto> getAll();
    void save(FaqDto dto);
    void update(FaqDto dto);
    void delete(FaqDto dto);
}
