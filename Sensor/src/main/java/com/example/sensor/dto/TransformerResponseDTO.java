package com.example.sensor.dto;
import lombok.Data;
@Data
public class TransformerResponseDTO {
        private Long id;
        private String serialNo;
        private String transformerName;
        private Double capacity;
        private String location;
        private String status;
}

