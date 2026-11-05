package com.vetcare.appointments.service;

import com.vetcare.appointments.config.ClinicProperties;
import org.springframework.stereotype.Service;

@Service
public class ClinicInfoService {

    private final ClinicProperties clinicProperties;

    public ClinicInfoService(ClinicProperties clinicProperties) {
        this.clinicProperties = clinicProperties;
    }

    public ClinicProperties getInfo() {
        return clinicProperties;
    }
}
