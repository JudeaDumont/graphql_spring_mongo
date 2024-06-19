package com.example.graphql.Config;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.data.method.annotation.support.AnnotatedControllerConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//this is for setting up @BatchMapping, and it looks really terrible to me.
public class AnnotatedControllerConfigurerConfig {
    @Bean
    public AnnotatedControllerConfigurer annotatedControllerConfigurer(ListableBeanFactory beanFactory) {
        AnnotatedControllerConfigurer controllerConfigurer = new AnnotatedControllerConfigurer();
        controllerConfigurer.addFormatterRegistrar((registry) -> ApplicationConversionService.addBeans(registry, beanFactory));
        controllerConfigurer.setExecutor((createBatchMappingExecutor()));
        return controllerConfigurer;
    }

    private ThreadPoolTaskExecutor createBatchMappingExecutor() {
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setPrestartAllCoreThreads(true);
        executor.initialize();
        return executor;
    }
}
