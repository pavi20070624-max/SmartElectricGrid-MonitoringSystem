package com.example.transformer.entity;
import com.example.transformer.enums.TransformerStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name="transformer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class Transformer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="SerialNo is required")
    @Column(unique = true,nullable = false)
    private String serialNo;

    @NotBlank(message="Transformer Name is required")
    @Column(unique = true,nullable = false)
    private String transformerName;

    @Positive(message ="Transformer Capacity must be greater than 0")
    @Column(nullable=false)
    private Integer capacity;

    @Column(nullable=false)
    @NotBlank(message="Location is required")
    private String location;

    @Enumerated(EnumType.STRING)
    private TransformerStatus status= TransformerStatus.ACTIVE;

}
