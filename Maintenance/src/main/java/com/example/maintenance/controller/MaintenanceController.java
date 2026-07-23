package com.example.maintenance.controller;

import com.example.maintenance.dto.MaintenanceRequestDTO;
import com.example.maintenance.dto.MaintenanceResponseDTO;
import com.example.maintenance.service.MaintenanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService service;

    @PostMapping
    public MaintenanceResponseDTO createMaintenance(@Valid @RequestBody MaintenanceRequestDTO requestDTO) {
        return service.createMaintenance(requestDTO);
    }
    @PutMapping("/{id}/complete")
    public MaintenanceResponseDTO completeMaintenance(@PathVariable Long id) {
        return service.completeMaintenance(id);
    }

    @GetMapping
    public List<MaintenanceResponseDTO> getAllMaintenance() {
        return service.getAllMaintenance();
    }

    @GetMapping("/{id}")
    public MaintenanceResponseDTO getMaintenanceById(@PathVariable Long id) {
        return service.getMaintenanceById(id);
    }

    @PutMapping("/{id}")
    public MaintenanceResponseDTO updateMaintenance(@PathVariable Long id,
                                                    @RequestBody MaintenanceRequestDTO requestDTO) {
        return service.updateMaintenance(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteMaintenance(@PathVariable Long id) {
        service.deleteMaintenance(id);
        return "Maintenance Record Deleted Successfully";
    }

}