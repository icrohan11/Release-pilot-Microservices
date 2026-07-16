package com.cts.releasepilot.qa.service;

import com.cts.releasepilot.qa.common.SuiteStatus;
import com.cts.releasepilot.qa.common.SuiteType;
import com.cts.releasepilot.qa.dto.TestSuiteRequestDTO;
import com.cts.releasepilot.qa.dto.TestSuiteResponseDTO;
import com.cts.releasepilot.qa.entity.TestSuite;
import com.cts.releasepilot.qa.exception.ResourceNotFoundException;
import com.cts.releasepilot.qa.mapper.TestSuiteMapper;
import com.cts.releasepilot.qa.repository.TestSuiteRepository;
import com.cts.releasepilot.qa.service.impl.TestSuiteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestSuiteServiceImplTest {

    @Mock private TestSuiteRepository testSuiteRepository;
    @Mock private TestSuiteMapper testSuiteMapper;
    @InjectMocks private TestSuiteServiceImpl testSuiteService;

    private TestSuite suite;

    @BeforeEach
    void setUp() {
        suite = new TestSuite(1L, 10L, "Login Regression", SuiteType.Regression, 25, SuiteStatus.Active);
    }

    @Test
    void getSuiteById_found() {
        when(testSuiteRepository.findById(1L)).thenReturn(Optional.of(suite));
        when(testSuiteMapper.toResponse(suite)).thenReturn(new TestSuiteResponseDTO());
        assertNotNull(testSuiteService.getSuiteById(1L));
    }

    @Test
    void getSuiteById_notFound_throws() {
        when(testSuiteRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> testSuiteService.getSuiteById(99L));
    }

    @Test
    void createSuite_success() {
        TestSuiteRequestDTO dto = new TestSuiteRequestDTO();
        dto.setProductID(10L);
        dto.setSuiteName("Login Regression");
        dto.setType(SuiteType.Regression);

        when(testSuiteMapper.toEntity(dto)).thenReturn(suite);
        when(testSuiteRepository.save(suite)).thenReturn(suite);
        when(testSuiteMapper.toResponse(suite)).thenReturn(new TestSuiteResponseDTO());

        assertNotNull(testSuiteService.createSuite(dto));
        verify(testSuiteRepository).save(suite);
    }

    @Test
    void getAllSuites_returnsMappedList() {
        when(testSuiteRepository.findAll()).thenReturn(List.of(suite));
        when(testSuiteMapper.toResponse(suite)).thenReturn(new TestSuiteResponseDTO());
        assertEquals(1, testSuiteService.getAllSuites().size());
    }

    @Test
    void deleteSuite_notFound_throws() {
        when(testSuiteRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> testSuiteService.deleteSuite(5L));
        verify(testSuiteRepository, never()).delete(any());
    }
}
