package org.example.demo.hello.controller;

import org.example.demo.hello.model.HelloRequestBo;
import org.example.demo.hello.model.HelloResponseBo;
import org.example.demo.hello.service.HelloService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author cwt
 * @description
 * @date 2025/9/14
 */
@RestController
public class HelloController {

    @Resource
    private HelloService helloServiceImpl;

    @PostMapping("/hello")
    public HelloResponseBo sayHello(@RequestBody HelloRequestBo requestBo) {
        return helloServiceImpl.sayHello(requestBo);
    }
}
