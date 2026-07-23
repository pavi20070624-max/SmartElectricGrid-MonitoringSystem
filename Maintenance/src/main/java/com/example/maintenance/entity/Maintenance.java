package com.example.maintenance.entity;
import com.example.maintenance.enums.MaintenanceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="maintenance")
public class Maintenance {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Long alertId;

        private String transformerSerial;

        private String issueDescription;

        @Enumerated(EnumType.STRING)
        private MaintenanceStatus status;

        private String assignedEngineer;

        private LocalDate scheduledDate;

        private LocalDate completedDate;

        private String remarks;

    }

