package com.example.maintenance.repository;

import com.example.maintenance.entity.Maintenance;
import com.example.maintenance.enums.MaintenanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    List<Maintenance> findByStatus(MaintenanceStatus status);

    List<Maintenance> findByTransformerSerial(String transformerSerial);

}
