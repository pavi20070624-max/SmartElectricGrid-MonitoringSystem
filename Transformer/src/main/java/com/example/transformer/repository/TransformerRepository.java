package com.example.transformer.repository;

import com.example.transformer.entity.Transformer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransformerRepository extends JpaRepository<Transformer,Long> {
    Optional<Transformer> findByserialNo(String serialNumber);
    boolean existsByserialNo(String serialNo);
}
