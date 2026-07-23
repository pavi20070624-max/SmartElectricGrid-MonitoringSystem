package com.example.sensor.controller;
import com.example.sensor.dto.SensorResponseDTO;
import com.example.sensor.service.SensorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.sensor.dto.SensorResponseDTO;
import com.example.sensor.dto.SensorRequestDTO;

import java.util.List;

@RestController
@RequestMapping("/sensor")

public class SensorController {
    @Autowired
    private SensorService sensorService;

    @PostMapping
    public SensorResponseDTO addSensor(@Valid @RequestBody SensorRequestDTO requestDTO) {
        return sensorService.addSensor(requestDTO);
    }

    @GetMapping
    public List<SensorResponseDTO> getAllSensors() {
        return sensorService.getAllSensors();
    }

    @GetMapping("/{id}")
    public SensorResponseDTO getSensorById(@PathVariable Long id) {
        return sensorService.getSensorById(id);
    }

//    @GetMapping("/transformer/{transformerId}")
//    public List<SensorResponseDTO> getSensorsByTransformerId(@PathVariable Long transformerId) {
//        return sensorService.getSensorsByTransformerId(transformerId);
//    }
    @GetMapping("/transformer/serial/{serialNo}")
    public List<SensorResponseDTO> getSensorsBySerialNo(
            @PathVariable String serialNo) {

        return sensorService.getSensorsBySerialNo(serialNo);
    }

    @PutMapping("/{id}")
    public SensorResponseDTO updateSensor(@PathVariable Long id,
                                          @Valid @RequestBody SensorRequestDTO requestDTO) {
        return sensorService.updateSensor(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteSensor(@PathVariable Long id) {
        sensorService.deleteSensor(id);
        return "Sensor deleted successfully.";
    }
}

