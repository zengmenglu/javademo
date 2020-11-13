package com.example.serviceA;

import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceAController {
    @GetMapping("/ping")
    public String ping(){
        System.out.println("被调用了一次");
        return "Pong";
    }

    @RequestMapping(value = "/sayHello/{name}", method = RequestMethod.GET)
    public String sayHello(@PathVariable("name") String name) throws InterruptedException {
        System.out.println("被调用了一次");
        return "{'msg': 'hello, " + name + "'}";
    }

    @RequestMapping(value = "/sayBey/{name}", method = RequestMethod.GET)
    public String sayBey(@PathVariable("name") String name) {
        System.out.println("被调用了一次");
        return "{'msg': 'bye, " + name + "'}";
    }
}
