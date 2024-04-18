package com.medipredict.challange.controller;

import com.medipredict.challange.cronjob.PersonLoggingJob;
import com.medipredict.challange.service.DataGenerationService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class DataGenerationController {

    private static final Logger logger = LogManager.getLogger(PersonLoggingJob.class);

    @Autowired
    private DataGenerationService dataGenerationService;

    @Operation(summary = "Generate Data", description = "Generate 5000 data items.")
    @GetMapping("/generate-data")
    public ResponseEntity<String> generateData() {
        logger.info("Generate 5000 data items.");
        dataGenerationService.generateData();
        return new ResponseEntity<>("Generated 5000 data", HttpStatus.OK);
    }
}
