package com.example.sensor.dto;

import com.example.sensor.enums.AlertType;
import com.example.sensor.enums.Severity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertResponseDTO {

    private Long id;

    private String alertCode;

    private Long sensorId;

    private String serialNo;

    private AlertType alertType;

    private Severity severity;

    private String message;

    private LocalDateTime createdAt;
}