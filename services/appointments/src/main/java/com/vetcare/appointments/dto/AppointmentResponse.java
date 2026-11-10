package com.vetcare.appointments.dto;

import java.time.Instant;
import java.util.UUID;

import com.vetcare.appointments.entity.Appointment;
import com.vetcare.appointments.entity.AppointmentStatus;

public record AppointmentResponse(UUID id, UUID petId, UUID veterinarianId, Instant scheduledAt, AppointmentStatus status) {

    public static AppointmentResponse from(Appointment appointment) {
        return new AppointmentResponse(
            appointment.getId(),
            appointment.getPetId(),
            appointment.getVeterinarianId(),
            appointment.getScheduledAt(),
            appointment.getStatus()
        );
    }
}
