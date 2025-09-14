package org.example.demo.pipeline.handler;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * @author cwt
 * @description
 * @date 2025/9/14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PipeLineHandler {
    /**
     * 制定handler所属于的pipeline
     * 一个handler可以对应多个pipeline
     */
    Qualifier[] belongPipeLines();
}
