package com.example.sensor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertRequestDTO {

    private Long sensorId;

    private String serialNo;

    private Double temperature;

    private Double voltage;

    private Double current;

    private Double oilLevel;
}