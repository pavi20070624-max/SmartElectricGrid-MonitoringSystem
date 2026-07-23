package com.example.maintenance.service;

import com.example.maintenance.dto.MaintenanceRequestDTO;
import com.example.maintenance.dto.MaintenanceResponseDTO;

import java.util.List;

public interface MaintenanceService {

    MaintenanceResponseDTO createMaintenance(MaintenanceRequestDTO requestDTO);

    List<MaintenanceResponseDTO> getAllMaintenance();

    MaintenanceResponseDTO getMaintenanceById(Long id);

    MaintenanceResponseDTO updateMaintenance(Long id, MaintenanceRequestDTO requestDTO);

    MaintenanceResponseDTO completeMaintenance(Long id);

    void deleteMaintenance(Long id);

}
