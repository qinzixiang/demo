package com.qinzx.demo.proxy;

import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author qinzx
 * @date 2020/06/22 17:35
 */
@Configuration
public class AopConfig {
    @Resource
    private SpringInterceptor springInterceptor;

    @Bean
    public RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor() {
        RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor = new RegexpMethodPointcutAdvisor();
        regexpMethodPointcutAdvisor.setPatterns(".*People");
        regexpMethodPointcutAdvisor.setAdvice(springInterceptor);
        return regexpMethodPointcutAdvisor;
    }
}