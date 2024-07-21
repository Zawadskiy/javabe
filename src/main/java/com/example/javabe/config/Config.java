package com.example.javabe.config;

import com.example.javabe.deserializer.CustomLocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.http.HttpClient;
import java.time.LocalDateTime;

@Configuration
@EnableScheduling
@EnableJpaRepositories("com.example.javabe")
public class Config {

    @Bean
    public HttpClient getHttpClient() {
        return HttpClient.newHttpClient();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule simpleModule = getJavaTimeModule().addDeserializer(LocalDateTime.class, getCustomLocalDateTimeDeserializer());
        objectMapper.registerModule(simpleModule);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper;
    }

    @Bean
    public CustomLocalDateTimeDeserializer getCustomLocalDateTimeDeserializer() {
        return new CustomLocalDateTimeDeserializer();
    }

    @Bean
    public JavaTimeModule getJavaTimeModule() {
        return new JavaTimeModule();
    }
}