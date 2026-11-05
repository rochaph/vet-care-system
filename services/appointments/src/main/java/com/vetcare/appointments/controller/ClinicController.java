package com.vetcare.appointments.controller;

import com.vetcare.appointments.config.ClinicProperties;
import com.vetcare.appointments.service.ClinicInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clinic")
public class ClinicController {

    private final ClinicInfoService clinicInfoService;

    public ClinicController(ClinicInfoService clinicInfoService) {
        this.clinicInfoService = clinicInfoService;
    }

    @GetMapping
    public ClinicProperties getInfo() {
        return clinicInfoService.getInfo();
    }
}
