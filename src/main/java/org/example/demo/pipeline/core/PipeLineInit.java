package org.example.demo.pipeline.core;

import org.apache.commons.lang3.StringUtils;
import org.example.demo.pipeline.handler.IHandler;
import org.example.demo.pipeline.handler.PipeLineHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cwt
 * @description
 * @date 2025/9/14
 */
@Component
public class PipeLineInit implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final Map<String, List<IHandler>> pipeLineMap = new HashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(PipeLineInit.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("-----pipeLine init start-----");

        Map<String, IHandler> orderHandlers = this.applicationContext.getBeansOfType(IHandler.class);
        for (Map.Entry<String, IHandler> entry : orderHandlers.entrySet()) {
            IHandler handler = entry.getValue();
            PipeLineHandler pipeLineHandler = handler.getClass().getAnnotation(PipeLineHandler.class);
            if (pipeLineHandler != null) {
                Qualifier[] qualifiers = pipeLineHandler.belongPipeLines();
                for (Qualifier qualifier : qualifiers) {
                    String pipeLineName = qualifier.value();
                    if (StringUtils.isNotBlank(pipeLineName)) {
                        if (pipeLineMap.containsKey(pipeLineName)) {
                            pipeLineMap.get(pipeLineName).add(handler);
                        } else {
                            List<IHandler> handlers = new ArrayList<>();
                            handlers.add(handler);
                            pipeLineMap.put(pipeLineName, handlers);
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, List<IHandler>> entry : pipeLineMap.entrySet()) {
            String pipeLineName = entry.getKey();
            List<IHandler> handlers = entry.getValue();

            DefaultListableBeanFactory defaultListableBeanFactory = null;
            AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
            if (autowireCapableBeanFactory instanceof DefaultListableBeanFactory) {
                defaultListableBeanFactory = (DefaultListableBeanFactory) autowireCapableBeanFactory;
            }
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DefaultPipeLine.class)
                    .addPropertyValue("handlers", handlers);
            BeanDefinition beanDefinition = builder.getBeanDefinition();
            defaultListableBeanFactory.registerBeanDefinition(pipeLineName, beanDefinition);
            LOGGER.info("beanName={}, registerBeanDefinition finish", pipeLineName);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
