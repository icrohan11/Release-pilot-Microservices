package com.cts.releasepilot.release.service.impl;

import com.cts.releasepilot.release.common.FreezeStatus;
import com.cts.releasepilot.release.dto.ChangeFreezeWindowRequestDTO;
import com.cts.releasepilot.release.dto.ChangeFreezeWindowResponseDTO;
import com.cts.releasepilot.release.entity.ChangeFreezeWindow;
import com.cts.releasepilot.release.exception.InvalidOperationException;
import com.cts.releasepilot.release.exception.ResourceNotFoundException;
import com.cts.releasepilot.release.mapper.ChangeFreezeWindowMapper;
import com.cts.releasepilot.release.repository.ChangeFreezeWindowRepository;
import com.cts.releasepilot.release.service.ChangeFreezeWindowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChangeFreezeWindowServiceImpl implements ChangeFreezeWindowService {

    private final ChangeFreezeWindowRepository changeFreezeWindowRepository;
    private final ChangeFreezeWindowMapper changeFreezeWindowMapper;

    public ChangeFreezeWindowServiceImpl(ChangeFreezeWindowRepository changeFreezeWindowRepository,
                                         ChangeFreezeWindowMapper changeFreezeWindowMapper) {
        this.changeFreezeWindowRepository = changeFreezeWindowRepository;
        this.changeFreezeWindowMapper = changeFreezeWindowMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChangeFreezeWindowResponseDTO> getAll() {
        return changeFreezeWindowRepository.findAll().stream().map(changeFreezeWindowMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeFreezeWindowResponseDTO getById(Long id) {
        return changeFreezeWindowMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public ChangeFreezeWindowResponseDTO create(ChangeFreezeWindowRequestDTO dto) {
        if (dto.getEndDate() != null && dto.getStartDate() != null
                && dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new InvalidOperationException("End date must not be before start date");
        }
        ChangeFreezeWindow entity = changeFreezeWindowMapper.toEntity(dto);
        entity.setFreezeID(null);
        if (entity.getStatus() == null) {
            entity.setStatus(FreezeStatus.Scheduled);
        }
        return changeFreezeWindowMapper.toResponse(changeFreezeWindowRepository.save(entity));
    }

    @Override
    @Transactional
    public ChangeFreezeWindowResponseDTO update(Long id, ChangeFreezeWindowRequestDTO dto) {
        ChangeFreezeWindow entity = findOrThrow(id);
        changeFreezeWindowMapper.updateEntity(entity, dto);
        return changeFreezeWindowMapper.toResponse(changeFreezeWindowRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ChangeFreezeWindow entity = findOrThrow(id);
        changeFreezeWindowRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChangeFreezeWindowResponseDTO> getByProduct(Long productID) {
        return changeFreezeWindowRepository.findByProductID(productID).stream()
                .map(changeFreezeWindowMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChangeFreezeWindowResponseDTO> getByStatus(FreezeStatus status) {
        return changeFreezeWindowRepository.findByStatus(status).stream()
                .map(changeFreezeWindowMapper::toResponse).toList();
    }

    private ChangeFreezeWindow findOrThrow(Long id) {
        return changeFreezeWindowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Change freeze window not found with ID: " + id));
    }
}
