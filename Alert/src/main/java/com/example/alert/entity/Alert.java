package com.example.alert.entity;

import com.example.alert.enums.AlertType;
import com.example.alert.enums.Severity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "alert")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String alertCode;

    @Column(nullable=false)
    private Long sensorId;

    @Column(nullable = false)
    private String serialNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertType alertType;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Severity severity;

    @Column(nullable=false)
    private String message;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}

