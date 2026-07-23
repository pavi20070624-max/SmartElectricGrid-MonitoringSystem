package com.example.transformer.service;

import com.example.transformer.dto.TransformerRequestDTO;
import com.example.transformer.dto.TransformerResponseDTO;

import java.util.List;

public interface TransformerService {

    List<TransformerResponseDTO> addTransformer(List<TransformerRequestDTO> requestDTO);

    List<TransformerResponseDTO> getAllTransformer();

    TransformerResponseDTO getTransformerById(Long id);

    TransformerResponseDTO getTransformerBySerialNumber(String serialNumber);

    TransformerResponseDTO updateTransformer(String serialNo,
                                             TransformerRequestDTO requestDTO);

    void deleteTransformer(Long id);

}