package com.medipredict.challange.service;

import com.medipredict.challange.model.BloodType;
import com.medipredict.challange.model.MedicalData;
import com.medipredict.challange.model.Person;
import com.medipredict.challange.model.repository.MedicalDataRepository;
import com.medipredict.challange.model.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DataGenerationService {

    private PersonRepository personRepository;

    private MedicalDataRepository medicalDataRepository;

    @Transactional
    public void generateData() {
        List<Person> persons = generatePersons(5000);
        personRepository.saveAll(persons); // Save persons to database

        // Save medical data associated with persons
        List<MedicalData> medicalDataList = persons.stream()
                .map(Person::getMedicalData)
                .collect(Collectors.toList());
        medicalDataRepository.saveAll(medicalDataList);
    }

    private List<Person> generatePersons(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> {
                    Person person = new Person();
                    person.setName("Person " + i);
                    person.setDateOfBirth(LocalDate.now().minusYears(new Random().nextInt(50) + 18));
                    person.setSocialSecurityNumber(generateRandomSSN());
                    person.setAddress("Address " + i);
                    person.setPhoneNumber("1234567890"); // Set a dummy phone number

                    MedicalData medicalData = new MedicalData();
                    medicalData.setBloodType(generateRandomBloodType());
                    medicalData.setBloodPressure(new Random().nextInt(150) + 60);
                    medicalData.setHeight(new Random().nextInt(100) + 120);
                    medicalData.setWeight(new Random().nextInt(150) + 50);
                    medicalData.setDiabetes(new Random().nextBoolean());
                    medicalData.setAntibioticsResistance(new Random().nextBoolean());
                    medicalData.setHypertonicTimeIndex(BigDecimal.valueOf(Math.random() * 10).setScale(2, BigDecimal.ROUND_HALF_UP));

                    // Associate MedicalData with Person
                    person.setMedicalData(medicalData);

                    return person;
                })
                .collect(Collectors.toList());
    }

    private String generateRandomSSN() {
        // Generate a random 9-digit number
        return String.format("%09d", new Random().nextInt(1000000000));
    }

    private BloodType generateRandomBloodType() {
        // Generate a random blood type from enum values
        BloodType[] bloodTypes = BloodType.values();
        return bloodTypes[new Random().nextInt(bloodTypes.length)];
    }

    @Autowired
    public DataGenerationService(PersonRepository personRepository, MedicalDataRepository medicalDataRepository) {
        this.personRepository = personRepository;
        this.medicalDataRepository = medicalDataRepository;
    }
}