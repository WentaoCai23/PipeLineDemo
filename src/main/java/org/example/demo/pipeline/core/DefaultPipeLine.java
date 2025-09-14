package org.example.demo.pipeline.core;

import lombok.Data;
import org.example.demo.pipeline.handler.IHandler;
import org.example.demo.pipeline.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author cwt
 * @description
 * @date 2025/9/14
 */
@Data
public class DefaultPipeLine implements IPipeLine<BaseRequestBo, BaseResponseBo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPipeLine.class);

    private List<IHandler<BaseRequestBo, BaseResponseBo>> handlers;

    @Override
    public BaseResponseBo pipeline(BaseRequestBo request) {
        WorkFlowContext<BaseRequestBo, BaseResponseBo> context = new WorkFlowContext<>();
        context.setRequestBody(request);
        context.setResponseBody(null);
        WorkFlowStateEvent workFlowStateEvent = new WorkFlowStateEvent();
        workFlowStateEvent.setWorkStateEnum(WorkStateEnum.WORKING);
        try {
            for (IHandler<BaseRequestBo, BaseResponseBo> handler : handlers) {
                Long start = System.currentTimeMillis();
                LOGGER.info("进入handler--->{}, uuid={}", handler.getClass().getName(), context.getRequestBody().getUuid());
                handler.execute(context, workFlowStateEvent);
                Long end = System.currentTimeMillis();
                LOGGER.info("结束handler--->{}, uuid={}, 耗时={}ms,", handler.getClass(), context.getRequestBody().getUuid(), end - start);
                if (WorkStateEnum.WORKING != workFlowStateEvent.getWorkStateEnum()) {
                    break;
                }
            }
        } catch (Exception e) {
            BaseResponseBo errorResponse = new BaseResponseBo();
            errorResponse.setUuid(context.getRequestBody().getUuid());
            context.setResponseBody(errorResponse);
            LOGGER.error("handler execute error", e);
        }
        return context.getResponseBody();
    }
}
