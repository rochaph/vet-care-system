package com.vetcare.appointments.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record CreateAppointmentRequest(@NotNull UUID petId, @NotNull UUID veterinarianId, @NotNull @Future OffsetDateTime scheduledAt) {
}
