package com.cts.releasepilot.communication.service;

import com.cts.releasepilot.communication.common.AdvisoryStatus;
import com.cts.releasepilot.communication.common.Severity;
import com.cts.releasepilot.communication.dto.CustomerAdvisoryRequestDTO;
import com.cts.releasepilot.communication.dto.CustomerAdvisoryResponseDTO;

import java.util.List;

/** Customer advisory operations. */
public interface CustomerAdvisoryService {

    List<CustomerAdvisoryResponseDTO> getAllAdvisories();

    CustomerAdvisoryResponseDTO getAdvisoryById(Long id);

    CustomerAdvisoryResponseDTO createAdvisory(CustomerAdvisoryRequestDTO dto);

    CustomerAdvisoryResponseDTO updateAdvisory(Long id, CustomerAdvisoryRequestDTO dto);

    void deleteAdvisory(Long id);

    List<CustomerAdvisoryResponseDTO> getAdvisoriesByRelease(Long releaseID);

    List<CustomerAdvisoryResponseDTO> getAdvisoriesBySeverity(Severity severity);

    List<CustomerAdvisoryResponseDTO> getAdvisoriesByStatus(AdvisoryStatus status);
}
