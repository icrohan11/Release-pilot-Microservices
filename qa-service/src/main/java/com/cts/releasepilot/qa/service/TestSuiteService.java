package com.cts.releasepilot.qa.service;

import com.cts.releasepilot.qa.common.SuiteStatus;
import com.cts.releasepilot.qa.common.SuiteType;
import com.cts.releasepilot.qa.dto.TestSuiteRequestDTO;
import com.cts.releasepilot.qa.dto.TestSuiteResponseDTO;

import java.util.List;

/** Test suite management operations. */
public interface TestSuiteService {

    List<TestSuiteResponseDTO> getAllSuites();

    TestSuiteResponseDTO getSuiteById(Long id);

    TestSuiteResponseDTO createSuite(TestSuiteRequestDTO dto);

    TestSuiteResponseDTO updateSuite(Long id, TestSuiteRequestDTO dto);

    void deleteSuite(Long id);

    List<TestSuiteResponseDTO> getSuitesByProduct(Long productID);

    List<TestSuiteResponseDTO> getSuitesByStatus(SuiteStatus status);

    List<TestSuiteResponseDTO> getSuitesByType(SuiteType type);
}
