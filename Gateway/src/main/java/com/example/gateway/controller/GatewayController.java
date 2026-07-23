package com.example.gateway.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

    private final RestTemplate restTemplate;

    public GatewayController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    // Get all transformers
    @GetMapping("/transformers")
    public String getAllTransformers() {
        return restTemplate.getForObject(
                "http://localhost:8081/transformers",
                String.class
        );
    }

    // Get transformer by Serial Number
    @GetMapping("/transformers/serial/{serialNo}")
    public String getTransformerBySerial(@PathVariable String serialNo) {
        return restTemplate.getForObject(
                "http://localhost:8081/transformers/serial/" + serialNo,
                String.class
        );
    }

    // Add Transformer
    @PostMapping("/transformers")
    public Object addTransformer(@RequestBody Object transformer) {
        return restTemplate.postForObject(
                "http://localhost:8081/transformers",
                transformer,
                Object.class
        );
    }

    // Update Transformer
    @PutMapping("/transformers/serial/{serialNo}")
    public void updateTransformer(
            @PathVariable String serialNo,
            @RequestBody Object transformer) {

        restTemplate.put(
                "http://localhost:8081/transformers/serial/" + serialNo,
                transformer
        );
    }

    // Delete Transformer
    @DeleteMapping("/transformers/{id}")
    public void deleteTransformer(@PathVariable Long id) {

        restTemplate.delete(
                "http://localhost:8081/transformers/" + id
        );
    }



    // Get all Sensors
    @GetMapping("/sensors")
    public String getAllSensors() {

        return restTemplate.getForObject(
                "http://localhost:8082/sensor",
                String.class
        );
    }

    // Add Sensor
    @PostMapping("/sensors")
    public Object addSensor(@RequestBody Object sensor) {

        return restTemplate.postForObject(
                "http://localhost:8082/sensor",
                sensor,
                Object.class
        );
    }
    @PostMapping("/alerts")
    public Object createAlert(@RequestBody Object alert) {

        return restTemplate.postForObject(
                "http://localhost:8083/alerts",
                alert,
                Object.class
        );
    }
    // Get all Alerts
    @GetMapping("/alerts")
    public String getAllAlerts() {

        return restTemplate.getForObject(
                "http://localhost:8083/alerts",
                String.class
        );
    }

    // Get Alert by ID
    @GetMapping("/alerts/{id}")
    public String getAlert(@PathVariable Long id) {

        return restTemplate.getForObject(
                "http://localhost:8083/alerts/" + id,
                String.class
        );
    }




    // Get all Maintenance Records
    @GetMapping("/maintenance")
    public String getAllMaintenance() {

        return restTemplate.getForObject(
                "http://localhost:8084/maintenance",
                String.class
        );
    }
    @GetMapping("/maintenance/{id}")
    public String maintenanceById(@PathVariable Long id) {

        return restTemplate.getForObject(
                "http://localhost:8084/maintenance/" + id,
                String.class
        );
    }

    // Create Maintenance
    @PostMapping("/maintenance")
    public Object createMaintenance(@RequestBody Object maintenance) {

        return restTemplate.postForObject(
                "http://localhost:8084/maintenance",
                maintenance,
                Object.class
        );

    }

    // Update Maintenance
    @PutMapping("/maintenance/{id}")
    public void updateMaintenance(
            @PathVariable Long id,
            @RequestBody Object maintenance) {

        restTemplate.put(
                "http://localhost:8084/maintenance/" + id,
                maintenance
        );

    }

    // Delete Maintenance
    @DeleteMapping("/maintenance/{id}")
    public void deleteMaintenance(@PathVariable Long id) {

        restTemplate.delete(
                "http://localhost:8084/maintenance/" + id
        );
    }
}