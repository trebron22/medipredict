import com.medipredict.challange.model.BloodType;
import com.medipredict.challange.model.MedicalData;
import com.medipredict.challange.model.repository.MedicalDataRepository;
import com.medipredict.challange.service.MedicalDataService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MedicalDataServiceTest {

    @Mock
    private MedicalDataRepository medicalDataRepository;

    @InjectMocks
    private MedicalDataService medicalDataService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllMedicalData() {
        // Given
        List<MedicalData> expectedMedicalData = new ArrayList<>();
        when(medicalDataRepository.findAll()).thenReturn(expectedMedicalData);

        // When
        List<MedicalData> actualMedicalData = medicalDataService.getAllMedicalData();

        // Then
        assertSame(expectedMedicalData, actualMedicalData);
        verify(medicalDataRepository).findAll();
    }

    @Test
    void getMedicalDataById_Exists() {
        // Given
        Long id = 1L;
        MedicalData expectedMedicalData = new MedicalData();
        when(medicalDataRepository.findById(id)).thenReturn(Optional.of(expectedMedicalData));

        // When
        MedicalData actualMedicalData = medicalDataService.getMedicalDataById(id);

        // Then
        assertSame(expectedMedicalData, actualMedicalData);
        verify(medicalDataRepository).findById(id);
    }

    @Test
    void getMedicalDataById_NotExists() {
        // Given
        Long id = 1L;
        when(medicalDataRepository.findById(id)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(EntityNotFoundException.class, () -> medicalDataService.getMedicalDataById(id));
        verify(medicalDataRepository).findById(id);
    }

    @Test
    void createMedicalData() {
        // Given
        MedicalData medicalData = new MedicalData();
        when(medicalDataRepository.save(medicalData)).thenReturn(medicalData);

        // When
        MedicalData createdMedicalData = medicalDataService.createMedicalData(medicalData);

        // Then
        assertSame(medicalData, createdMedicalData);
        verify(medicalDataRepository).save(medicalData);
    }


    @Test
    void updateMedicalData_NotExists() {
        // Given
        Long id = 1L;
        when(medicalDataRepository.findById(id)).thenReturn(Optional.empty());
        MedicalData updatedMedicalData = new MedicalData();

        // When/Then
        assertThrows(EntityNotFoundException.class, () -> medicalDataService.updateMedicalData(id, updatedMedicalData));
        verify(medicalDataRepository).findById(id);
        verify(medicalDataRepository, never()).save(any());
    }

    @Test
    void deleteMedicalData_Exists() {
        // Given
        Long id = 1L;
        MedicalData existingMedicalData = new MedicalData();
        existingMedicalData.setId(id);
        when(medicalDataRepository.findById(id)).thenReturn(Optional.of(existingMedicalData));

        // When
        medicalDataService.deleteMedicalData(id);

        // Then
        verify(medicalDataRepository).deleteById(id);
    }

}
