package com.example.maintenance.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenanceRequestDTO {

    @NotNull(message = "Alert ID is required")
    private Long alertId;

    private String assignedEngineer;

    private LocalDate scheduledDate;

    private String remarks;

}