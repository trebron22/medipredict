package com.medipredict.challange.service;

import com.medipredict.challange.model.Person;
import com.medipredict.challange.model.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Service
public class PersonService {

    private static final Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        logger.info("Fetching all persons");
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        logger.info("Fetching person by ID: {}", id);
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));
    }

    public Person createPerson(Person person) {
        logger.info("Creating person: {}", person);
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person updatedPerson) {
        logger.info("Updating person with ID: {}", id);
        Person existingPerson = getPersonById(id);
        existingPerson.setName(updatedPerson.getName());
        existingPerson.setDateOfBirth(updatedPerson.getDateOfBirth());
        existingPerson.setSocialSecurityNumber(updatedPerson.getSocialSecurityNumber());
        existingPerson.setAddress(updatedPerson.getAddress());
        existingPerson.setPhoneNumber(updatedPerson.getPhoneNumber());
        return personRepository.save(existingPerson);
    }

    public void deletePerson(Long id) {
        logger.info("Deleting person with ID: {}", id);
        personRepository.deleteById(id);
    }
}
