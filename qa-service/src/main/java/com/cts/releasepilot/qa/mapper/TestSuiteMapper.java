package com.cts.releasepilot.qa.mapper;

import com.cts.releasepilot.qa.dto.TestSuiteRequestDTO;
import com.cts.releasepilot.qa.dto.TestSuiteResponseDTO;
import com.cts.releasepilot.qa.entity.TestSuite;
import org.springframework.stereotype.Component;

/** Converts between {@link TestSuite} entities and their DTOs. */
@Component
public class TestSuiteMapper {

    public TestSuite toEntity(TestSuiteRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        TestSuite suite = new TestSuite();
        suite.setProductID(dto.getProductID());
        suite.setSuiteName(dto.getSuiteName());
        suite.setType(dto.getType());
        suite.setTotalTestCases(dto.getTotalTestCases());
        suite.setStatus(dto.getStatus());
        return suite;
    }

    public void updateEntity(TestSuite suite, TestSuiteRequestDTO dto) {
        suite.setProductID(dto.getProductID());
        suite.setSuiteName(dto.getSuiteName());
        suite.setType(dto.getType());
        suite.setTotalTestCases(dto.getTotalTestCases());
        suite.setStatus(dto.getStatus());
    }

    public TestSuiteResponseDTO toResponse(TestSuite suite) {
        if (suite == null) {
            return null;
        }
        TestSuiteResponseDTO dto = new TestSuiteResponseDTO();
        dto.setSuiteID(suite.getSuiteID());
        dto.setProductID(suite.getProductID());
        dto.setSuiteName(suite.getSuiteName());
        dto.setType(suite.getType());
        dto.setTotalTestCases(suite.getTotalTestCases());
        dto.setStatus(suite.getStatus());
        return dto;
    }
}
