package com.example.alert.controller;

import com.example.alert.dto.AlertRequestDTO;
import com.example.alert.dto.AlertResponseDTO;
import com.example.alert.service.AlertService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    private final AlertService service;

    public AlertController(AlertService service) {
        this.service = service;
    }

    @PostMapping
    public AlertResponseDTO createAlert(@Valid @RequestBody AlertRequestDTO requestDTO) {
        return service.createAlert(requestDTO);
    }

    @GetMapping
    public List<AlertResponseDTO> getAllAlerts() {
        return service.getAllAlerts();
    }

    @GetMapping("/{id}")
    public AlertResponseDTO getAlertById(@PathVariable Long id) {
        return service.getAlertById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAlert(@PathVariable Long id) {
        service.deleteAlert(id);
        return "Alert deleted successfully.";
    }
}