package com.example.alert.dto;

import com.example.alert.enums.AlertType;
import com.example.alert.enums.Severity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertRequestDTO {

    @NotNull(message = "Sensor ID is required")
    private Long sensorId;

    @NotBlank(message = "Serial Number is required")
    private String serialNo;

    @NotNull(message = "Temperature is required")
    private Double temperature;

    @NotNull(message = "Voltage is required")
    private Double voltage;

    @NotNull(message = "Current is required")
    private Double current;

    @NotNull(message = "Oil Level is required")
    private Double oilLevel;
}