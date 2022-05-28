package com.degenerator.hackinhome.service.faq;

import com.degenerator.hackinhome.dao.faq.FaqRepository;
import com.degenerator.hackinhome.dto.FaqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FaqService implements IFaqService{

    @Autowired
    private FaqRepository repository;

    @Override
    public List<FaqDto> getAll() {
        return repository.findAll();
    }

    @Override
    public void save(FaqDto dto) {
        repository.save(dto);
    }

    @Transactional
    @Override
    public void update(FaqDto dto) {
        FaqDto dtoFromDb =  repository.findById(dto.getId()).orElse(new FaqDto());
        dtoFromDb.setQuestion(dto.getQuestion());
        dtoFromDb.setAnswer(dto.getAnswer());
        repository.save(dtoFromDb);
    }

    @Override
    public void delete(FaqDto dto) {
        repository.delete(dto);
    }

}
