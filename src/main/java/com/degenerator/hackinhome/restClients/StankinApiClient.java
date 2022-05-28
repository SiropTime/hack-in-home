package com.degenerator.hackinhome.restClients;

import com.degenerator.hackinhome.dto.DepartmentDto;
import com.degenerator.hackinhome.dto.TeacherDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "stankinApi", url = "${stankin.api}")
public interface StankinApiClient {

/*    @RequestMapping(method = RequestMethod.GET, value = "/employee")
    List<TeacherDto> getTeachersList();

    @RequestMapping(method = RequestMethod.GET, value = "/departments")
    List<DepartmentDto> getDepartmentsList();*/

}
