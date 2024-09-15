package com.dimas.eurekaclientservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of("message", "hello, world");
    }

    @GetMapping("/check-instance")
    public Map<String, String> hello1() {
        return Map.of("instance", instanceId);
    }
}
