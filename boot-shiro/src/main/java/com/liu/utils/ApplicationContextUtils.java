package com.liu.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 目的获取运行中的整个spring工厂，然后根据指定的bean名字进行获取指容器中的bean对象
 * 就类似于原生的spring通过配置文件获取bean工厂的方式
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    // 获取指定的bean对象
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }

}
