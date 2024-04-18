package com.medipredict.challange.service;

import com.medipredict.challange.model.MedicalData;
import com.medipredict.challange.model.repository.MedicalDataRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalDataService {

    private static final Logger logger = LogManager.getLogger(MedicalDataService.class);

    @Autowired
    private MedicalDataRepository medicalDataRepository;

    public List<MedicalData> getAllMedicalData() {
        logger.info("Retrieving all medical data");
        return medicalDataRepository.findAll();
    }

    public MedicalData getMedicalDataById(Long id) {
        logger.info("Retrieving medical data by ID: {}", id);
        return medicalDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MedicalData not found with id: " + id));
    }

    public MedicalData createMedicalData(MedicalData medicalData) {
        logger.info("Creating medical data");
        return medicalDataRepository.save(medicalData);
    }

    public MedicalData updateMedicalData(Long id, MedicalData updatedMedicalData) {
        logger.info("Updating medical data with ID: {}", id);
        MedicalData existingMedicalData = getMedicalDataById(id);
        existingMedicalData.setBloodType(updatedMedicalData.getBloodType());
        existingMedicalData.setBloodPressure(updatedMedicalData.getBloodPressure());
        existingMedicalData.setHeight(updatedMedicalData.getHeight());
        existingMedicalData.setWeight(updatedMedicalData.getWeight());
        existingMedicalData.setDiabetes(updatedMedicalData.isDiabetes());
        existingMedicalData.setAntibioticsResistance(updatedMedicalData.isAntibioticsResistance());
        existingMedicalData.setHypertonicTimeIndex(updatedMedicalData.getHypertonicTimeIndex());
        return medicalDataRepository.save(existingMedicalData);
    }

    public void deleteMedicalData(Long id) {
        logger.info("Deleting medical data with ID: {}", id);
        medicalDataRepository.deleteById(id);
    }
}
