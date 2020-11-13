package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
@ConfigurationProperties(prefix = "person")
@PropertySource(value = {"classpath:config.yml"})
public class YmlParse {
    @Value("${icon}")
    private String name;
    @Value("${age}")
    private int age;

    @GetMapping("/yml")
    public  String sayYaml(){
        return  "yml: "+name+age;
    }
}
