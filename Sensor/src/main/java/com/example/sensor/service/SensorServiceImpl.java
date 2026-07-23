package com.example.sensor.service;

import com.example.sensor.dto.SensorRequestDTO;
import com.example.sensor.dto.SensorResponseDTO;
import com.example.sensor.entity.Sensor;
import com.example.sensor.exception.ResourceNotFoundException;
import com.example.sensor.repository.SensorRepository;
import com.example.sensor.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.example.sensor.dto.TransformerResponseDTO;
import org.springframework.web.client.HttpClientErrorException;

import com.example.sensor.dto.AlertRequestDTO;
import com.example.sensor.dto.AlertResponseDTO;
import com.example.sensor.enums.AlertType;
import com.example.sensor.enums.Severity;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {
    @Autowired
    private final SensorRepository sensorRepository;
    private final RestTemplate restTemplate;
    

        @Override
        public SensorResponseDTO addSensor(SensorRequestDTO requestDTO) {

            TransformerResponseDTO transformer;

            try {
                transformer = restTemplate.getForObject(
                        "http://localhost:8081/transformers/serial/" + requestDTO.getSerialNo(),
                        TransformerResponseDTO.class
                );

            } catch (HttpClientErrorException.NotFound e) {
                throw new ResourceNotFoundException(
                        "Transformer not found with Serial No: " + requestDTO.getSerialNo()
                );
            }

            Sensor sensor = new Sensor();

            sensor.setTransformerId(transformer.getId());
            sensor.setSerialNo(requestDTO.getSerialNo());
            sensor.setVoltage(requestDTO.getVoltage());
            sensor.setCurrent(requestDTO.getCurrent());
            sensor.setTemperature(requestDTO.getTemperature());
            sensor.setOilLevel(requestDTO.getOilLevel());
            sensor.setRecordedAt(LocalDateTime.now());

            Sensor savedSensor = sensorRepository.save(sensor);

            // ---------------- ALERT INTEGRATION ----------------

            if (savedSensor.getTemperature() > 80
                    || savedSensor.getVoltage() < 200
                    || savedSensor.getVoltage() > 260
                    || savedSensor.getCurrent() > 100
                    || savedSensor.getOilLevel() < 20) {

                AlertRequestDTO alertRequest = new AlertRequestDTO();

                alertRequest.setSensorId(savedSensor.getId());
                alertRequest.setSerialNo(savedSensor.getSerialNo());
                alertRequest.setTemperature(savedSensor.getTemperature());
                alertRequest.setVoltage(savedSensor.getVoltage());
                alertRequest.setCurrent(savedSensor.getCurrent());
                alertRequest.setOilLevel(savedSensor.getOilLevel());

                restTemplate.postForObject(
                        "http://localhost:8083/alerts",
                        alertRequest,
                        AlertResponseDTO.class
                );
            }

            // ---------------- RESPONSE ----------------

            SensorResponseDTO responseDTO = new SensorResponseDTO();

            responseDTO.setId(savedSensor.getId());
            responseDTO.setSerialNo(savedSensor.getSerialNo());
            responseDTO.setTransformerId(savedSensor.getTransformerId());
            responseDTO.setVoltage(savedSensor.getVoltage());
            responseDTO.setCurrent(savedSensor.getCurrent());
            responseDTO.setTemperature(savedSensor.getTemperature());
            responseDTO.setOilLevel(savedSensor.getOilLevel());
            responseDTO.setRecordedAt(savedSensor.getRecordedAt());

            return responseDTO;
        }

    @Override
    public List<SensorResponseDTO> getAllSensors() {
        List<Sensor> sensors = sensorRepository.findAll();
        List<SensorResponseDTO> responseList = new ArrayList<>();

        for (Sensor sensor : sensors) {

            SensorResponseDTO responseDTO = new SensorResponseDTO();

            responseDTO.setId(sensor.getId());
            responseDTO.setSerialNo(sensor.getSerialNo());
            responseDTO.setTransformerId(sensor.getTransformerId());
            responseDTO.setVoltage(sensor.getVoltage());
            responseDTO.setCurrent(sensor.getCurrent());
            responseDTO.setTemperature(sensor.getTemperature());
            responseDTO.setOilLevel(sensor.getOilLevel());
            responseDTO.setRecordedAt(sensor.getRecordedAt());

            responseList.add(responseDTO);
        }

        return responseList;
    }

    @Override
    public SensorResponseDTO getSensorById(Long id) {

        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found with ID: " + id));

        SensorResponseDTO responseDTO = new SensorResponseDTO();

        responseDTO.setId(sensor.getId());
        responseDTO.setSerialNo(sensor.getSerialNo());
        responseDTO.setTransformerId(sensor.getTransformerId());
        responseDTO.setVoltage(sensor.getVoltage());
        responseDTO.setCurrent(sensor.getCurrent());
        responseDTO.setTemperature(sensor.getTemperature());
        responseDTO.setOilLevel(sensor.getOilLevel());
        responseDTO.setRecordedAt(sensor.getRecordedAt());

        return responseDTO;
    }
    @Override
    public List<SensorResponseDTO> getSensorsBySerialNo(String serialNo) {
        return new ArrayList<>();
    }
//    @Override
//    public List<SensorResponseDTO> getSensorsByTransformerId(Long transformerId) {
//
//        List<Sensor> sensors = sensorRepository.findByTransformerId(transformerId);
//        List<SensorResponseDTO> responseList = new ArrayList<>();
//
//        for (Sensor sensor : sensors) {
//
//            SensorResponseDTO responseDTO = new SensorResponseDTO();
//
//            responseDTO.setId(sensor.getId());
//            responseDTO.setSerialNo(sensor.getSerialNo());
//            responseDTO.setVoltage(sensor.getVoltage());
//            responseDTO.setCurrent(sensor.getCurrent());
//            responseDTO.setTemperature(sensor.getTemperature());
//            responseDTO.setOilLevel(sensor.getOilLevel());
//            responseDTO.setRecordedAt(sensor.getRecordedAt());
//
//            responseList.add(responseDTO);
//        }
//
//        return responseList;
//    }

    @Override
    public SensorResponseDTO updateSensor(Long id, SensorRequestDTO requestDTO) {
        TransformerResponseDTO transformer;

        try {
            transformer = restTemplate.getForObject(
                    "http://localhost:8081/transformers/serial/" + requestDTO.getSerialNo(),
                    TransformerResponseDTO.class
            );

        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException(
                    "Transformer not found with Serial No: " + requestDTO.getSerialNo()
            );
        }

        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found with ID: " + id));
        sensor.setSerialNo(requestDTO.getSerialNo());
        sensor.setTransformerId(transformer.getId());
        sensor.setVoltage(requestDTO.getVoltage());
        sensor.setCurrent(requestDTO.getCurrent());
        sensor.setTemperature(requestDTO.getTemperature());
        sensor.setOilLevel(requestDTO.getOilLevel());
        sensor.setRecordedAt(LocalDateTime.now());

        Sensor updatedSensor = sensorRepository.save(sensor);

        SensorResponseDTO responseDTO = new SensorResponseDTO();

        responseDTO.setId(updatedSensor.getId());
        responseDTO.setSerialNo(updatedSensor.getSerialNo());
        responseDTO.setTransformerId(updatedSensor.getTransformerId());
        responseDTO.setVoltage(updatedSensor.getVoltage());
        responseDTO.setCurrent(updatedSensor.getCurrent());
        responseDTO.setTemperature(updatedSensor.getTemperature());
        responseDTO.setOilLevel(updatedSensor.getOilLevel());
        responseDTO.setRecordedAt(updatedSensor.getRecordedAt());

        return responseDTO;
    }

    @Override
    public void deleteSensor(Long id) {

        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found with ID: " + id));

        sensorRepository.delete(sensor);
    }
}