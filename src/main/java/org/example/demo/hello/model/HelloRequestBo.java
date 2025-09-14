package org.example.demo.hello.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.demo.pipeline.model.BaseRequestBo;

/**
 * @author cwt
 * @description
 * @date 2025/9/14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HelloRequestBo extends BaseRequestBo {
    private String name;
}
