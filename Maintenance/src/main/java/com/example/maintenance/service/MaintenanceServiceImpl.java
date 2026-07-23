package com.example.maintenance.service;

import com.example.maintenance.dto.AlertResponseDTO;
import com.example.maintenance.dto.MaintenanceRequestDTO;
import com.example.maintenance.dto.MaintenanceResponseDTO;
import com.example.maintenance.dto.TransformerResponseDTO;
import com.example.maintenance.entity.Maintenance;
import com.example.maintenance.enums.MaintenanceStatus;
import com.example.maintenance.exceptions.MaintenanceNotFoundException;
import com.example.maintenance.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository repository;
    private final RestTemplate restTemplate;

    @Override
    public MaintenanceResponseDTO createMaintenance(MaintenanceRequestDTO requestDTO) {

        AlertResponseDTO alert = restTemplate.getForObject(
                "http://localhost:8083/alerts/" + requestDTO.getAlertId(),
                AlertResponseDTO.class
        );

        if (alert == null) {
            throw new RuntimeException("Alert Not Found");
        }

        TransformerResponseDTO transformer = restTemplate.getForObject(
                "http://localhost:8081/transformers/serial/" + alert.getSerialNo(),
                TransformerResponseDTO.class
        );

        if (transformer == null) {
            throw new RuntimeException("Transformer Not Found");
        }

        Maintenance maintenance = new Maintenance();

        maintenance.setAlertId(alert.getId());
        maintenance.setTransformerSerial(alert.getSerialNo());
        maintenance.setIssueDescription(alert.getMessage());
        maintenance.setAssignedEngineer(requestDTO.getAssignedEngineer());
        maintenance.setScheduledDate(requestDTO.getScheduledDate());
        maintenance.setRemarks(requestDTO.getRemarks());
        maintenance.setStatus(MaintenanceStatus.PENDING);

        repository.save(maintenance);

        MaintenanceResponseDTO responseDTO = new MaintenanceResponseDTO();

        responseDTO.setId(maintenance.getId());
        responseDTO.setAlertId(maintenance.getAlertId());
        responseDTO.setTransformerSerial(maintenance.getTransformerSerial());
        responseDTO.setIssueDescription(maintenance.getIssueDescription());
        responseDTO.setStatus(maintenance.getStatus());
        responseDTO.setAssignedEngineer(maintenance.getAssignedEngineer());
        responseDTO.setScheduledDate(maintenance.getScheduledDate());
        responseDTO.setCompletedDate(maintenance.getCompletedDate());
        responseDTO.setRemarks(maintenance.getRemarks());

        return responseDTO;
    }

    @Override
    public List<MaintenanceResponseDTO> getAllMaintenance() {

        List<Maintenance> maintenanceList = repository.findAll();
        List<MaintenanceResponseDTO> responseList = new ArrayList<>();

        for (Maintenance maintenance : maintenanceList) {

            MaintenanceResponseDTO responseDTO = new MaintenanceResponseDTO();

            responseDTO.setId(maintenance.getId());
            responseDTO.setAlertId(maintenance.getAlertId());
            responseDTO.setTransformerSerial(maintenance.getTransformerSerial());
            responseDTO.setIssueDescription(maintenance.getIssueDescription());
            responseDTO.setStatus(maintenance.getStatus());
            responseDTO.setAssignedEngineer(maintenance.getAssignedEngineer());
            responseDTO.setScheduledDate(maintenance.getScheduledDate());
            responseDTO.setCompletedDate(maintenance.getCompletedDate());
            responseDTO.setRemarks(maintenance.getRemarks());

            responseList.add(responseDTO);
        }

        return responseList;
    }

    @Override
    public MaintenanceResponseDTO getMaintenanceById(Long id) {

        Maintenance maintenance = repository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException("Maintenance Not Found"));

        MaintenanceResponseDTO responseDTO = new MaintenanceResponseDTO();

        responseDTO.setId(maintenance.getId());
        responseDTO.setAlertId(maintenance.getAlertId());
        responseDTO.setTransformerSerial(maintenance.getTransformerSerial());
        responseDTO.setIssueDescription(maintenance.getIssueDescription());
        responseDTO.setStatus(maintenance.getStatus());
        responseDTO.setAssignedEngineer(maintenance.getAssignedEngineer());
        responseDTO.setScheduledDate(maintenance.getScheduledDate());
        responseDTO.setCompletedDate(maintenance.getCompletedDate());
        responseDTO.setRemarks(maintenance.getRemarks());

        return responseDTO;
    }

    @Override
    public MaintenanceResponseDTO updateMaintenance(Long id, MaintenanceRequestDTO requestDTO) {

        Maintenance maintenance = repository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException("Maintenance Not Found"));

        maintenance.setAssignedEngineer(requestDTO.getAssignedEngineer());
        maintenance.setScheduledDate(requestDTO.getScheduledDate());
        maintenance.setRemarks(requestDTO.getRemarks());

        repository.save(maintenance);

        MaintenanceResponseDTO responseDTO = new MaintenanceResponseDTO();

        responseDTO.setId(maintenance.getId());
        responseDTO.setAlertId(maintenance.getAlertId());
        responseDTO.setTransformerSerial(maintenance.getTransformerSerial());
        responseDTO.setIssueDescription(maintenance.getIssueDescription());
        responseDTO.setStatus(maintenance.getStatus());
        responseDTO.setAssignedEngineer(maintenance.getAssignedEngineer());
        responseDTO.setScheduledDate(maintenance.getScheduledDate());
        responseDTO.setCompletedDate(maintenance.getCompletedDate());
        responseDTO.setRemarks(maintenance.getRemarks());

        return responseDTO;
    }
    @Override
    public MaintenanceResponseDTO completeMaintenance(Long id) {

        Maintenance maintenance = repository.findById(id)
                .orElseThrow(() ->
                        new MaintenanceNotFoundException("Maintenance Not Found"));

        maintenance.setStatus(MaintenanceStatus.COMPLETED);
        maintenance.setCompletedDate(java.time.LocalDate.now());

        repository.save(maintenance);

        MaintenanceResponseDTO responseDTO = new MaintenanceResponseDTO();

        responseDTO.setId(maintenance.getId());
        responseDTO.setAlertId(maintenance.getAlertId());
        responseDTO.setTransformerSerial(maintenance.getTransformerSerial());
        responseDTO.setIssueDescription(maintenance.getIssueDescription());
        responseDTO.setStatus(maintenance.getStatus());
        responseDTO.setAssignedEngineer(maintenance.getAssignedEngineer());
        responseDTO.setScheduledDate(maintenance.getScheduledDate());
        responseDTO.setCompletedDate(maintenance.getCompletedDate());
        responseDTO.setRemarks(maintenance.getRemarks());

        return responseDTO;
    }

    @Override
    public void deleteMaintenance(Long id) {

        Maintenance maintenance = repository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException("Maintenance Not Found"));

        repository.delete(maintenance);
    }

}