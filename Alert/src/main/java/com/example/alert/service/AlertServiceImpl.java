package com.example.alert.service;

import com.example.alert.dto.AlertRequestDTO;
import com.example.alert.dto.AlertResponseDTO;
import com.example.alert.entity.Alert;
import com.example.alert.exceptions.AlertNotFoundException;
import com.example.alert.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.example.alert.enums.*;
@Service
public class AlertServiceImpl implements AlertService {

    private final AlertRepository repository;

    public AlertServiceImpl(AlertRepository repository) {
        this.repository = repository;
    }

    private String generateAlertCode() {
        long count = repository.count() + 1;
        return String.format("ALT%03d", count);
    }

    private AlertResponseDTO mapToResponseDTO(Alert alert) {

        AlertResponseDTO responseDTO = new AlertResponseDTO();

        responseDTO.setId(alert.getId());
        responseDTO.setAlertCode(alert.getAlertCode());
        responseDTO.setSensorId(alert.getSensorId());
        responseDTO.setSerialNo(alert.getSerialNo());
        responseDTO.setAlertType(alert.getAlertType());
        responseDTO.setSeverity(alert.getSeverity());
        responseDTO.setMessage(alert.getMessage());
        responseDTO.setCreatedAt(alert.getCreatedAt());

        return responseDTO;
    }

    @Override
    public AlertResponseDTO createAlert(AlertRequestDTO requestDTO) {

        Alert alert = new Alert();

        alert.setAlertCode(generateAlertCode());
        alert.setSensorId(requestDTO.getSensorId());
        alert.setSerialNo((requestDTO.getSerialNo()));
        AlertType alertType = determineAlertType(requestDTO);

        if (alertType == null) {
            throw new IllegalArgumentException("No abnormal sensor reading detected.");
        }
        Severity severity = determineSeverity(requestDTO);

        alert.setAlertType(alertType);
        alert.setSeverity(severity);
        alert.setMessage(generateMessage(alertType));
        alert.setCreatedAt(LocalDateTime.now());

        Alert savedAlert = repository.save(alert);

        return mapToResponseDTO(savedAlert);
    }

    @Override
    public List<AlertResponseDTO> getAllAlerts() {

        List<Alert> alerts = repository.findAll();

        return alerts.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlertResponseDTO getAlertById(Long id) {

        Alert alert = repository.findById(id)
                .orElseThrow(() ->
                        new AlertNotFoundException("Alert not found with ID : " + id));

        return mapToResponseDTO(alert);
    }

    @Override
    public void deleteAlert(Long id) {

        Alert alert = repository.findById(id)
                .orElseThrow(() ->
                        new AlertNotFoundException("Alert not found with ID : " + id));

        repository.delete(alert);
    }
    private AlertType determineAlertType(AlertRequestDTO requestDTO) {

        if (requestDTO.getTemperature() > 80) {
            return AlertType.HIGH_TEMPERATURE;
        }

        if (requestDTO.getVoltage() < 200) {
            return AlertType.LOW_VOLTAGE;
        }

        if (requestDTO.getVoltage() > 260) {
            return AlertType.HIGH_VOLTAGE;
        }

        if (requestDTO.getCurrent() > 100) {
            return AlertType.CURRENT_SPIKE;
        }

        if (requestDTO.getOilLevel() < 20) {
            return AlertType.OVERLOAD;
        }

        return null;
    }
    private Severity determineSeverity(AlertRequestDTO requestDTO) {

        if (requestDTO.getTemperature() > 100) {
            return Severity.CRITICAL;
        }

        if (requestDTO.getTemperature() > 80) {
            return Severity.HIGH;
        }

        if (requestDTO.getVoltage() < 180) {
            return Severity.CRITICAL;
        }

        if (requestDTO.getVoltage() < 200) {
            return Severity.HIGH;
        }

        if (requestDTO.getVoltage() > 280) {
            return Severity.CRITICAL;
        }

        if (requestDTO.getVoltage() > 260) {
            return Severity.HIGH;
        }

        if (requestDTO.getCurrent() > 120) {
            return Severity.CRITICAL;
        }

        if (requestDTO.getCurrent() > 100) {
            return Severity.HIGH;
        }

        if (requestDTO.getOilLevel() < 10) {
            return Severity.CRITICAL;
        }

        if (requestDTO.getOilLevel() < 20) {
            return Severity.HIGH;
        }

        return Severity.HIGH;
    }
    private String generateMessage(AlertType alertType) {

        switch (alertType) {

            case HIGH_TEMPERATURE:
                return "Transformer temperature exceeded safe limit.";

            case LOW_VOLTAGE:
                return "Transformer voltage dropped below safe limit.";

            case HIGH_VOLTAGE:
                return "Transformer voltage exceeded safe limit.";

            case CURRENT_SPIKE:
                return "Current spike detected.";

            case OVERLOAD:
                return "Transformer overload detected.";

            default:
                return "Unknown alert.";
        }
    }
}