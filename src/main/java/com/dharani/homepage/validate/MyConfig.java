package com.dharani.homepage.validate;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public Validate getValidate()
    {
        return new Validate();
    }
}