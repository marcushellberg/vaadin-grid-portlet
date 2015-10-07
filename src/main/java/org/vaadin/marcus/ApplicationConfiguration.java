package org.vaadin.marcus;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"org.vaadin.marcus"})
public class ApplicationConfiguration {
	
    @Configuration
    @PropertySource("classpath:application.properties")
    static class ApplicationProperties {}
    
}