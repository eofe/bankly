package com.bankly.dto;

import java.time.LocalDate;
import java.util.UUID;

public record ClientDTO(UUID id, String firstName, String lastName, LocalDate dob, String email, String phone) {
}
