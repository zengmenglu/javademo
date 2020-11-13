package com.zml.ribbonnacos;

import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceController {
    @GetMapping("/ping")
    public String ping(){
        System.out.println("被调用了一次");
        return "Pong";
    }

    @RequestMapping(value = "/sayHello/{name}", method = RequestMethod.GET)
    public String sayHello(@PathVariable("name") String name) throws InterruptedException {
        return "{'serverB:8090 msg': 'hello, " + name + "'}";
    }
}
