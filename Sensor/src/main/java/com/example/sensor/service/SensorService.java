package com.example.sensor.service;
import com.example.sensor.dto.SensorRequestDTO;
import com.example.sensor.dto.SensorResponseDTO;
import java.util.List;

public interface SensorService {
    List<SensorResponseDTO> getSensorsBySerialNo(String serialNo);

    SensorResponseDTO addSensor(SensorRequestDTO requestDTO);

    List<SensorResponseDTO> getAllSensors();

    SensorResponseDTO getSensorById(Long id);


    SensorResponseDTO updateSensor(Long id, SensorRequestDTO requestDTO);

    void deleteSensor(Long id);
}
