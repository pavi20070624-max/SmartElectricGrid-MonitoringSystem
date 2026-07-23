package com.example.maintenance.dto;

import com.example.maintenance.enums.MaintenanceStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenanceResponseDTO {

    private Long id;

    private Long alertId;

    private String transformerSerial;

    private String issueDescription;

    private MaintenanceStatus status;

    private String assignedEngineer;

    private LocalDate scheduledDate;

    private LocalDate completedDate;

    private String remarks;

}