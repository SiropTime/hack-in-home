package com.degenerator.hackinhome.service.util;

import com.degenerator.hackinhome.dao.teacher.TeacherRepository;
import com.degenerator.hackinhome.dto.DepartmentDto;
import com.degenerator.hackinhome.dto.TeacherDto;
import com.degenerator.hackinhome.restClients.StankinApiClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StankinApiService {
    @Value("${stankin.api}")
    private String apiUrl;
    @Autowired
    private StankinApiClient stankinApiClient;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ModelMapper mapper;

    private RestTemplate restTemplate = new RestTemplate();

//    @Scheduled(fixedDelay = 10000, initialDelay = 1)
//    public void updateTeachersList(){
//        ResponseEntity<DepartmentDto[]> data = restTemplate.getForEntity(apiUrl + "/department", DepartmentDto[].class);
//
//        System.out.println(data);
//    }
}
