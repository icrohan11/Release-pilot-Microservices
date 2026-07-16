package com.cts.releasepilot.release.service.impl;

import com.cts.releasepilot.release.common.PromotionStatus;
import com.cts.releasepilot.release.dto.PromotionRequestRequestDTO;
import com.cts.releasepilot.release.dto.PromotionRequestResponseDTO;
import com.cts.releasepilot.release.entity.PromotionRequest;
import com.cts.releasepilot.release.exception.ResourceNotFoundException;
import com.cts.releasepilot.release.mapper.PromotionRequestMapper;
import com.cts.releasepilot.release.repository.PromotionRequestRepository;
import com.cts.releasepilot.release.service.PromotionRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PromotionRequestServiceImpl implements PromotionRequestService {

    private final PromotionRequestRepository promotionRequestRepository;
    private final PromotionRequestMapper promotionRequestMapper;

    public PromotionRequestServiceImpl(PromotionRequestRepository promotionRequestRepository,
                                       PromotionRequestMapper promotionRequestMapper) {
        this.promotionRequestRepository = promotionRequestRepository;
        this.promotionRequestMapper = promotionRequestMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PromotionRequestResponseDTO> getAll() {
        return promotionRequestRepository.findAll().stream().map(promotionRequestMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PromotionRequestResponseDTO getById(Long id) {
        return promotionRequestMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public PromotionRequestResponseDTO create(PromotionRequestRequestDTO dto) {
        PromotionRequest entity = promotionRequestMapper.toEntity(dto);
        entity.setPromotionID(null);
        if (entity.getStatus() == null) {
            entity.setStatus(PromotionStatus.Pending);
        }
        return promotionRequestMapper.toResponse(promotionRequestRepository.save(entity));
    }

    @Override
    @Transactional
    public PromotionRequestResponseDTO update(Long id, PromotionRequestRequestDTO dto) {
        PromotionRequest entity = findOrThrow(id);
        promotionRequestMapper.updateEntity(entity, dto);
        return promotionRequestMapper.toResponse(promotionRequestRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        PromotionRequest entity = findOrThrow(id);
        promotionRequestRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PromotionRequestResponseDTO> getByRelease(Long releaseID) {
        return promotionRequestRepository.findByReleaseID(releaseID).stream()
                .map(promotionRequestMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PromotionRequestResponseDTO> getByStatus(PromotionStatus status) {
        return promotionRequestRepository.findByStatus(status).stream()
                .map(promotionRequestMapper::toResponse).toList();
    }

    private PromotionRequest findOrThrow(Long id) {
        return promotionRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion request not found with ID: " + id));
    }
}
