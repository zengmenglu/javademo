package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
@ConfigurationProperties(prefix = "obj")
@PropertySource(value = {"classpath:config.yaml"})
public class YamlParse {
    @Value("${title}")
    private String title;

    @GetMapping("/yaml")
    public String sayYaml(){
        return "yaml:"+title;
    }
}
