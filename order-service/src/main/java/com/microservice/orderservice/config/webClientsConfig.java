package com.microservice.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class webClientsConfig {

    @Bean
    public WebClient webClients(){
        return WebClient.builder().build();
    }

    }


