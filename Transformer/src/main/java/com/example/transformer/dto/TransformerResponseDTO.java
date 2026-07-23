package com.example.transformer.dto;

import com.example.transformer.enums.TransformerStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransformerResponseDTO {
    private Long id;
    private String serialNo;
    private String transformerName;
    private Integer capacity;
    private String location;
    private TransformerStatus status;
    
}
