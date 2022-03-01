package com.hardziyevich.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "endpoint.userorder")
public record EndpointUserOrderProperties(String findBusyTime, String save) {
}
