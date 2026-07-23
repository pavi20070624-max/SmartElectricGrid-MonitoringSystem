package com.example.sensor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorResponseDTO {

    private Long id;

    private String serialNo;

    private long transformerId;

    private Double voltage;

    private Double current;

    private Double temperature;

    private Double oilLevel;

    private LocalDateTime recordedAt;
}
