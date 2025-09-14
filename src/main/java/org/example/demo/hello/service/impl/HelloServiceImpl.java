package org.example.demo.hello.service.impl;

import org.example.demo.hello.model.HelloRequestBo;
import org.example.demo.hello.model.HelloResponseBo;
import org.example.demo.hello.service.HelloService;
import org.example.demo.pipeline.core.DefaultPipeLine;
import org.example.demo.pipeline.model.BaseResponseBo;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author cwt
 * @description
 * @date 2025/9/14
 */
@Service
@DependsOn("pipeLineInit")
public class HelloServiceImpl implements HelloService {

    @Resource(name = "helloPipeLine")
    private DefaultPipeLine helloPipeLine;

    @Override
    public HelloResponseBo sayHello(HelloRequestBo requestBo) {
        BaseResponseBo<HelloResponseBo> responseBo = helloPipeLine.pipeline(requestBo);
        return responseBo.getData();
    }
}
