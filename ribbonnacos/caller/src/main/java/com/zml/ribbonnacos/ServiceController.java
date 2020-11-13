package com.zml.ribbonnacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/ping")
    public String ping(){
        System.out.println("被调用了一次");
        return "Pong";
    }


    @RequestMapping(value = "/ToA/{name}", method = RequestMethod.GET)
    public String sayHelloA(@PathVariable("name") String name) throws InterruptedException {
        return restTemplate.getForObject("http://serviceA/sayHello/"+name,String.class);
    }

    @RequestMapping(value = "/ToB/{name}", method = RequestMethod.GET)
    public String sayHelloB(@PathVariable("name") String name) throws InterruptedException {
        return restTemplate.getForObject("http://serviceB/sayHello/"+name,String.class);
    }
}
