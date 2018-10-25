package com.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import nz.net.ultraq.thymeleaf.decorators.strategies.GroupingStrategy;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class StartCRMApp 
{
    public static void main( String[] args )
    {
        //SpringApplication.run(StartCRMApp.class,args);
        SpringApplication app = new SpringApplication(StartCRMApp.class);
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
    }
    
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect(new GroupingStrategy());
    }
}
