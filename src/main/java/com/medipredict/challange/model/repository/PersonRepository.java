package com.medipredict.challange.model.repository;

import com.medipredict.challange.model.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllById(Long id, Pageable pageable); // pageable test
}
