package com.medipredict.challange.controller;

import com.medipredict.challange.model.MedicalData;
import com.medipredict.challange.service.MedicalDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-data")
public class MedicalDataRestController {

    private static final Logger logger = LogManager.getLogger(MedicalDataRestController.class);
    @Autowired
    private MedicalDataService medicalDataService;

    @Operation(summary = "Get all medical data", description = "Retrieve a list of all medical data.")
    @GetMapping
    public ResponseEntity<List<MedicalData>> getAllMedicalData() {
        logger.info("Retrieved all medical data");
        List<MedicalData> medicalDataList = medicalDataService.getAllMedicalData();
        return ResponseEntity.ok().body(medicalDataList);
    }

    @Operation(summary = "Get medical data by ID", description = "Retrieve medical data by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<MedicalData> getMedicalDataById(
            @Parameter(description = "Medical Data ID") @PathVariable Long id) {
        logger.info("Retrieved medical data by ID: {}", id);
        MedicalData medicalData = medicalDataService.getMedicalDataById(id);
        return ResponseEntity.ok().body(medicalData);
    }

    @Operation(summary = "Create medical data", description = "Create new medical data.")
    @PostMapping
    public ResponseEntity<MedicalData> createMedicalData(
            @Parameter(description = "Medical Data to create", required = true) @RequestBody MedicalData medicalData) {
        logger.info("Created medical data with ID: {}", medicalData.getId());
        MedicalData createdMedicalData = medicalDataService.createMedicalData(medicalData);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedicalData);
    }

    @Operation(summary = "Update medical data", description = "Update existing medical data by ID.")
    @PutMapping("/{id}")
    public ResponseEntity<MedicalData> updateMedicalData(
            @Parameter(description = "Medical Data ID") @PathVariable Long id,
            @Parameter(description = "Updated Medical Data", required = true) @RequestBody MedicalData updatedMedicalData) {
        logger.info("Updated medical data with ID: {}", id);
        MedicalData medicalData = medicalDataService.updateMedicalData(id, updatedMedicalData);
        return ResponseEntity.ok().body(medicalData);
    }

    @Operation(summary = "Delete medical data", description = "Delete medical data by ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalData(
            @Parameter(description = "Medical Data ID") @PathVariable Long id) {
        logger.info("Deleted medical data with ID: {}", id);
        medicalDataService.deleteMedicalData(id);
        return ResponseEntity.noContent().build();
    }
}
