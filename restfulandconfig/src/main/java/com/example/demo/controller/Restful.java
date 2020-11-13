package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Restful {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public  String sayhello(){
        return "hello zml";
    }

    @RequestMapping(value = "/bye", method = RequestMethod.GET)
    public  String saybye(){
        return "byb donut";
    }

    @GetMapping("/ha")
    public String sayha(){
        return "haha";
    }

    @Value("${name}")
    private String name;
    @GetMapping("/getproperty")
    public String sayProperty(){
        return "this is "+name;
    }
}


