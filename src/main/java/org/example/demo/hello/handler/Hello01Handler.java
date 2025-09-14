package org.example.demo.hello.handler;

import org.example.demo.hello.model.HelloRequestBo;
import org.example.demo.hello.model.HelloResponseBo;
import org.example.demo.pipeline.handler.IHandler;
import org.example.demo.pipeline.handler.PipeLineHandler;
import org.example.demo.pipeline.model.BaseResponseBo;
import org.example.demo.pipeline.model.WorkFlowContext;
import org.example.demo.pipeline.model.WorkFlowStateEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author cwt
 * @description
 * @date 2025/9/14
 */
@Order(1)
@PipeLineHandler(belongPipeLines = {@Qualifier("helloPipeLine")})
@Service
public class Hello01Handler implements IHandler<HelloRequestBo, BaseResponseBo<HelloResponseBo>> {
    @Override
    public void execute(WorkFlowContext<HelloRequestBo, BaseResponseBo<HelloResponseBo>> context, WorkFlowStateEvent workFlowStateEvent) {
        String uuid = context.getRequestBody().getUuid();
        String name = context.getRequestBody().getName();
        BaseResponseBo<HelloResponseBo> responseBo = new BaseResponseBo<>();
        responseBo.setUuid(uuid);
        HelloResponseBo helloResponseBo = new HelloResponseBo();
        helloResponseBo.setMessage(name);
        responseBo.setData(helloResponseBo);
        context.setResponseBody(responseBo);
    }
}
