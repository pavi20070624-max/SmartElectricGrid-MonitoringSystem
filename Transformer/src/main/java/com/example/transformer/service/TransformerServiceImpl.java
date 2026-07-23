package com.example.transformer.service;

import com.example.transformer.dto.TransformerRequestDTO;
import com.example.transformer.dto.TransformerResponseDTO;
import com.example.transformer.entity.Transformer;
import com.example.transformer.enums.TransformerStatus;
import com.example.transformer.exception.DuplicateIdException;
import com.example.transformer.exception.TransformerNotFoundException;
import com.example.transformer.repository.TransformerRepository;
import org.hibernate.boot.model.internal.CreateKeySecondPass;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransformerServiceImpl implements TransformerService {

    private final TransformerRepository repository;
    public TransformerServiceImpl(TransformerRepository repository){
        this.repository=repository;
    }

    @Override
    public List<TransformerResponseDTO> addTransformer(List<TransformerRequestDTO> requestDTOs) {

        List<TransformerResponseDTO> responseList = new ArrayList<>();

        for (TransformerRequestDTO dto : requestDTOs) {

            if (repository.existsByserialNo(dto.getSerialNo())) {
                throw new DuplicateIdException("Serial Number " + dto.getSerialNo() + " already exists");
            }

            Transformer transformer = new Transformer();
            transformer.setSerialNo(dto.getSerialNo());
            transformer.setTransformerName(dto.getTransformerName());
            transformer.setCapacity(dto.getCapacity());
            transformer.setLocation(dto.getLocation());
            transformer.setStatus(TransformerStatus.ACTIVE);

            Transformer savedTransformer = repository.save(transformer);

            TransformerResponseDTO responseDTO = new TransformerResponseDTO();
            responseDTO.setId(savedTransformer.getId());
            responseDTO.setSerialNo(savedTransformer.getSerialNo());
            responseDTO.setTransformerName(savedTransformer.getTransformerName());
            responseDTO.setCapacity(savedTransformer.getCapacity());
            responseDTO.setLocation(savedTransformer.getLocation());
            responseDTO.setStatus(savedTransformer.getStatus());

            responseList.add(responseDTO);
        }

        return responseList;

    }


    @Override
    public List<TransformerResponseDTO> getAllTransformer() {
        List <Transformer> transformers = repository.findAll();
        List <TransformerResponseDTO> responseDTOList = new ArrayList<>();
        for(Transformer transformer:transformers){
            TransformerResponseDTO responseDTO = new TransformerResponseDTO();
            responseDTO.setId(transformer.getId());
            responseDTO.setLocation(transformer.getLocation());
            responseDTO.setTransformerName(transformer.getTransformerName());
            responseDTO.setCapacity(transformer.getCapacity());
            responseDTO.setStatus(transformer.getStatus());
            responseDTO.setSerialNo(transformer.getSerialNo());
            responseDTOList.add(responseDTO);

        }
        return responseDTOList;
    }
    @Override
    public TransformerResponseDTO getTransformerById(Long id) {
        Optional<Transformer> optionalTransformer =
                repository.findById(id);
        if(optionalTransformer.isEmpty()){
            throw new TransformerNotFoundException("Transformer not found");
        }
        Transformer transformer = optionalTransformer.get();
        TransformerResponseDTO responseDTO= new TransformerResponseDTO();
        responseDTO.setId(transformer.getId());
        responseDTO.setSerialNo(transformer.getSerialNo());
        responseDTO.setTransformerName((transformer.getTransformerName()));
        responseDTO.setCapacity(transformer.getCapacity());
        responseDTO.setStatus(transformer.getStatus());
        responseDTO.setLocation(transformer.getLocation());

        return responseDTO;
    }

    @Override
    public TransformerResponseDTO updateTransformer(String serialNo,
                                                    TransformerRequestDTO requestDTO) {

        Transformer transformer = repository.findByserialNo(serialNo)
                .orElseThrow(() ->
                        new TransformerNotFoundException(
                                "Transformer not found with serial number: " + serialNo));

        transformer.setSerialNo(requestDTO.getSerialNo());
        transformer.setTransformerName(requestDTO.getTransformerName());
        transformer.setCapacity(requestDTO.getCapacity());
        transformer.setLocation(requestDTO.getLocation());

        Transformer updatedTransformer = repository.save(transformer);

        TransformerResponseDTO responseDTO = new TransformerResponseDTO();
        responseDTO.setId(updatedTransformer.getId());
        responseDTO.setSerialNo(updatedTransformer.getSerialNo());
        responseDTO.setTransformerName(updatedTransformer.getTransformerName());
        responseDTO.setCapacity(updatedTransformer.getCapacity());
        responseDTO.setLocation(updatedTransformer.getLocation());
        responseDTO.setStatus(updatedTransformer.getStatus());

        return responseDTO;
    }
    @Override
    public TransformerResponseDTO getTransformerBySerialNumber(String serialNo) {

        Transformer transformer = repository
                .findByserialNo(serialNo)
                .orElseThrow(() ->
                        new TransformerNotFoundException("Transformer not found with serial number: " + serialNo));

        TransformerResponseDTO responseDTO = new TransformerResponseDTO();

        responseDTO.setId(transformer.getId());
        responseDTO.setSerialNo(transformer.getSerialNo());
        responseDTO.setLocation(transformer.getLocation());
        responseDTO.setCapacity(transformer.getCapacity());
        responseDTO.setStatus(transformer.getStatus());

        return responseDTO;
    }

    @Override
    public void deleteTransformer(Long id) {
        Optional<Transformer> optionalTransformer = repository.findById(id);

        if(optionalTransformer.isEmpty()){
            throw new RuntimeException("Transformer not found");
        }

        repository.delete(optionalTransformer.get());
    }

}
