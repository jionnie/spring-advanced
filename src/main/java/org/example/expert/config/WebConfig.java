package org.example.expert.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.expert.aspects.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthUserArgumentResolver());
    }

    @Bean
    public LoggingAspect loggingAspect(ObjectMapper objectMapper) {
        return new LoggingAspect(objectMapper);
    }
}
