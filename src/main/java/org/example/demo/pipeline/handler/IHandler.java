package org.example.demo.pipeline.handler;

import org.example.demo.pipeline.model.WorkFlowContext;
import org.example.demo.pipeline.model.WorkFlowStateEvent;

/**
 * @author cwt
 * @description
 * @date 2025/9/14
 */
public interface IHandler<REQ, RES> {
    /**
     * 执行handler
     */
    void execute(WorkFlowContext<REQ, RES> context, WorkFlowStateEvent workFlowStateEvent);
}
