package com.example.serviceB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("ping")
    public String ping(){
        return "Pong";
    }

    @GetMapping("/sayHello")
    public String sayHello(String name){
        String url = "http://ServiceA/sayHello/" + name;
        String result = restTemplate.getForObject(url , String.class);
        return result;
    }

}