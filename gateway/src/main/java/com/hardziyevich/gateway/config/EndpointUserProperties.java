package com.hardziyevich.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "endpoint.user")
public record EndpointUserProperties(String findById, String findByNameAndLast) {
}
