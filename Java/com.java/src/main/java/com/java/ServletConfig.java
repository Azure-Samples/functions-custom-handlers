package com.java;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            int portNumber = Integer.parseInt(System.getenv("FUNCTIONS_HTTPWORKER_PORT"));
            container.setPort(portNumber);
        });
    }
}