import com.medipredict.challange.model.Person;
import com.medipredict.challange.model.repository.PersonRepository;
import com.medipredict.challange.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPersons() {
        // Given
        List<Person> expectedPersons = new ArrayList<>();
        when(personRepository.findAll()).thenReturn(expectedPersons);

        // When
        List<Person> actualPersons = personService.getAllPersons();

        // Then
        assertSame(expectedPersons, actualPersons);
        verify(personRepository).findAll();
    }

    @Test
    void getPersonById_Exists() {
        // Given
        Long id = 1L;
        Person expectedPerson = new Person();
        when(personRepository.findById(id)).thenReturn(Optional.of(expectedPerson));

        // When
        Person actualPerson = personService.getPersonById(id);

        // Then
        assertSame(expectedPerson, actualPerson);
        verify(personRepository).findById(id);
    }

    @Test
    void getPersonById_NotExists() {
        // Given
        Long id = 1L;
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(EntityNotFoundException.class, () -> personService.getPersonById(id));
        verify(personRepository).findById(id);
    }

    @Test
    void createPerson() {
        // Given
        Person person = new Person();
        when(personRepository.save(person)).thenReturn(person);

        // When
        Person createdPerson = personService.createPerson(person);

        // Then
        assertSame(person, createdPerson);
        verify(personRepository).save(person);
    }


    @Test
    void updatePerson_NotExists() {
        // Given
        Long id = 1L;
        when(personRepository.findById(id)).thenReturn(Optional.empty());
        Person updatedPerson = new Person();

        // When/Then
        assertThrows(EntityNotFoundException.class, () -> personService.updatePerson(id, updatedPerson));
        verify(personRepository).findById(id);
        verify(personRepository, never()).save(any());
    }

    @Test
    void deletePerson_Exists() {
        // Given
        Long id = 1L;
        Person existingPerson = new Person();
        when(personRepository.findById(id)).thenReturn(Optional.of(existingPerson));

        // When
        personService.deletePerson(id);

        // Then
        verify(personRepository).deleteById(id);
    }

}