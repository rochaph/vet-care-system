package com.vetcare.appointments.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "vetcare.clinic")
public record ClinicProperties(String name, String openingHours) {
}
