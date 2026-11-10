package com.vetcare.appointments.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CreateAppointmentRequest(UUID petId, UUID veterinarianId, OffsetDateTime scheduledAt) {
}
