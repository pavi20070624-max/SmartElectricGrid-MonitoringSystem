package com.example.transformer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransformerRequestDTO {
    @NotBlank(message="Serial No is required")
    private String serialNo;

    @NotBlank(message="Transformer Name is Required")
    private String transformerName;

    @Positive(message="Capacity must be greater than 0")
    private Integer capacity;

    @NotBlank(message = "Location is Required")
    private String location;
    
}
