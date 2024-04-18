package com.medipredict.challange.controller;

import com.medipredict.challange.model.Person;
import com.medipredict.challange.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonRestController {

    private static final Logger logger = LogManager.getLogger(PersonRestController.class);

    @Autowired
    private PersonService personService;

    @Operation(summary = "Get all persons", description = "Retrieve a list of all persons.")
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        logger.info("Retrieved all persons");
        List<Person> persons = personService.getAllPersons();
        return ResponseEntity.ok().body(persons);
    }

    @Operation(summary = "Get person by ID", description = "Retrieve a person by their ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(
            @Parameter(description = "Person ID") @PathVariable Long id) {
        logger.info("Retrieved person by ID: {}", id);
        Person person = personService.getPersonById(id);
        return ResponseEntity.ok().body(person);
    }

    @Operation(summary = "Create a person", description = "Create a new person.")
    @PostMapping
    public ResponseEntity<Person> createPerson(
            @Parameter(description = "Person to create", required = true) @RequestBody Person person) {
        logger.info("Created person with ID: {}", person.getId());
        Person createdPerson = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    @Operation(summary = "Update a person", description = "Update an existing person by ID.")
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(
            @Parameter(description = "Person ID") @PathVariable Long id,
            @Parameter(description = "Updated Person", required = true) @RequestBody Person updatedPerson) {
        logger.info("Updated person with ID: {}", id);
        Person person = personService.updatePerson(id, updatedPerson);
        return ResponseEntity.ok().body(person);
    }

    @Operation(summary = "Delete a person", description = "Delete a person by ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(
            @Parameter(description = "Person ID") @PathVariable Long id) {
        logger.info("Deleted person with ID: {}", id);
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}