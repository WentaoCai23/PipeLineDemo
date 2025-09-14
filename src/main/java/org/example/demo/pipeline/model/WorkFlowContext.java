package org.example.demo.pipeline.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cwt
 * @description
 * @date 2025/9/14
 */
@Data
public class WorkFlowContext<REQ, RES> {

    /**
     * 输入
     */
    private REQ requestBody;

    /**
     * 输出
     */
    private RES responseBody;

    /**
     * 用于参数传递
     */
    private final Map<String, Object> parameters = new HashMap<>();

    public <T> T getParameter(String key, T defaultValue) {
        Object value = parameters.get(key);
        if (value != null) {
            return (T) value;
        }
        return defaultValue;
    }

    public <T> T getParameter(String key) {
        Object value = parameters.get(key);
        if (value != null) {
            return (T) value;
        }
        return null;
    }

    public Map<String, Object> setParameter(String key, Object value) {
        parameters.put(key, value);
        return parameters;
    }
}
