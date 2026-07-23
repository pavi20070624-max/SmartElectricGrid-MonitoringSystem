package com.example.maintenance.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransformerResponseDTO {

    private Long id;

    private String serialNumber;

    private String location;

    private Double capacity;

    private String status;

}
