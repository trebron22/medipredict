package com.medipredict.challange.model.repository;

import com.medipredict.challange.model.MedicalData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalDataRepository extends JpaRepository<MedicalData, Long> {
    List<MedicalData> findAllById(Long id, Pageable pageable);
}
