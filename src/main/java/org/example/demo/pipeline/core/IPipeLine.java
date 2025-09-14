package org.example.demo.pipeline.core;

/**
 * @author cwt
 * @description
 * @date 2025/9/14
 */
public interface IPipeLine<REQ, RES> {
    RES pipeline(REQ request);
}
