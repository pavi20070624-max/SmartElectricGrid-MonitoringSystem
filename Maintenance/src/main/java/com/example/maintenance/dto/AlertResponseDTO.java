package com.example.maintenance.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AlertResponseDTO {

    private Long id;

    private String alertCode;

    private Long sensorId;

    private String serialNo;

    private String alertType;

    private String severity;

    private String message;

    private LocalDateTime createdAt;

}