package com.hardziyevich.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "endpoint.groomer")
public record EndpointGroomerProperties(
        String all,
        String byDay,
        String byService,
        String byDayAndService,
        String findWorkingTime,
        String findService,
        String findDay) {
}
