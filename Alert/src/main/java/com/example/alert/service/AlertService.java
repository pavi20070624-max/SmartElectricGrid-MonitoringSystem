package com.example.alert.service;

import com.example.alert.dto.AlertRequestDTO;
import com.example.alert.dto.AlertResponseDTO;

import java.util.List;

public interface AlertService  {
    AlertResponseDTO createAlert(AlertRequestDTO requestDTO);

    List<AlertResponseDTO> getAllAlerts();

    AlertResponseDTO getAlertById(Long id);

    void deleteAlert(Long id);
}
