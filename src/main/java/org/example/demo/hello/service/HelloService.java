package org.example.demo.hello.service;

import org.example.demo.hello.model.HelloRequestBo;
import org.example.demo.hello.model.HelloResponseBo;

/**
 * @author cwt
 * @description
 * @date 2025/9/14
 */
public interface HelloService {
    HelloResponseBo sayHello(HelloRequestBo requestBo);
}
