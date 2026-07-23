package com.example.sensor.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SensorRequestDTO {
    

    @NotBlank(message = "Serial Number is required")
    private String serialNo;

    @NotNull(message = "Voltage is required")
    @Positive(message = "Voltage must be positive")
    private Double voltage;

    @NotNull(message = "Current is required")
    @Positive(message = "Current must be positive")
    private Double current;

    @NotNull(message = "Temperature is required")
    private Double temperature;

    @NotNull(message = "Oil level is required")
    @DecimalMin(value = "0.0", message = "Oil level cannot be less than 0")
    @DecimalMax(value = "100.0", message = "Oil level cannot be greater than 100")
    private Double oilLevel;

}