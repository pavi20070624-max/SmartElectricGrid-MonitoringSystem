package com.example.transformer.controller;

import com.example.transformer.dto.TransformerRequestDTO;
import com.example.transformer.dto.TransformerResponseDTO;
import com.example.transformer.service.TransformerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transformers")
public class TransformerController {

    private final TransformerService service;

    @GetMapping("/serial/{serialNumber}")
    public TransformerResponseDTO getTransformerBySerialNumber(
            @PathVariable String serialNumber) {

        return service.getTransformerBySerialNumber(serialNumber);
    }
    public TransformerController(TransformerService service) {
        this.service = service;
    }

    // Add Transformer
    @PostMapping
    public List<TransformerResponseDTO> addTransformer( @Valid @RequestBody List<TransformerRequestDTO> requestDTOs) {
        return service.addTransformer(requestDTOs);
    }

    // Get All Transformers
    @GetMapping
    public List<TransformerResponseDTO> getAllTransformers() {
        return service.getAllTransformer();
    }

    // Get Transformer By Id
    @GetMapping("/{id}")
    public TransformerResponseDTO getTransformerById(@PathVariable Long id) {
        return service.getTransformerById(id);
    }

    // Update Transformer
// Update Transformer by Serial Number
    @PutMapping("/serial/{serialNo}")
    public TransformerResponseDTO updateTransformer(
            @PathVariable String serialNo,
            @RequestBody TransformerRequestDTO requestDTO) {

        return service.updateTransformer(serialNo, requestDTO);
    }

    // Delete Transformer
    @DeleteMapping("/{id}")
    public void deleteTransformer(@PathVariable Long id) {
        service.deleteTransformer(id);
    }
}