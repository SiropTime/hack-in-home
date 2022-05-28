package com.degenerator.hackinhome.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ApplicationConfiguration {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
