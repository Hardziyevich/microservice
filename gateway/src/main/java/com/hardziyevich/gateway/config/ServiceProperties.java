package com.hardziyevich.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service")
public record ServiceProperties(String groomerServiceUrl, String groomerDayUrl, String userGroomerUrl, String userSaveUrl, String orderUserUrl) {
}
