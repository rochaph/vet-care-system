package com.vetcare.appointments.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vetcare.appointments.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID>{

}
