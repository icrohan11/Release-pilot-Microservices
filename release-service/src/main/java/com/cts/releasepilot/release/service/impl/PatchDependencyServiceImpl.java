package com.cts.releasepilot.release.service.impl;

import com.cts.releasepilot.release.dto.PatchDependencyRequestDTO;
import com.cts.releasepilot.release.dto.PatchDependencyResponseDTO;
import com.cts.releasepilot.release.entity.PatchDependency;
import com.cts.releasepilot.release.exception.ResourceNotFoundException;
import com.cts.releasepilot.release.mapper.PatchDependencyMapper;
import com.cts.releasepilot.release.repository.PatchDependencyRepository;
import com.cts.releasepilot.release.service.PatchDependencyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatchDependencyServiceImpl implements PatchDependencyService {

    private final PatchDependencyRepository patchDependencyRepository;
    private final PatchDependencyMapper patchDependencyMapper;

    public PatchDependencyServiceImpl(PatchDependencyRepository patchDependencyRepository,
                                      PatchDependencyMapper patchDependencyMapper) {
        this.patchDependencyRepository = patchDependencyRepository;
        this.patchDependencyMapper = patchDependencyMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatchDependencyResponseDTO> getAll() {
        return patchDependencyRepository.findAll().stream().map(patchDependencyMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PatchDependencyResponseDTO getById(Long id) {
        return patchDependencyMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public PatchDependencyResponseDTO create(PatchDependencyRequestDTO dto) {
        PatchDependency entity = patchDependencyMapper.toEntity(dto);
        entity.setDependencyID(null);
        return patchDependencyMapper.toResponse(patchDependencyRepository.save(entity));
    }

    @Override
    @Transactional
    public PatchDependencyResponseDTO update(Long id, PatchDependencyRequestDTO dto) {
        PatchDependency entity = findOrThrow(id);
        patchDependencyMapper.updateEntity(entity, dto);
        return patchDependencyMapper.toResponse(patchDependencyRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        PatchDependency entity = findOrThrow(id);
        patchDependencyRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatchDependencyResponseDTO> getByRelease(Long releaseID) {
        return patchDependencyRepository.findByReleaseID(releaseID).stream()
                .map(patchDependencyMapper::toResponse).toList();
    }

    private PatchDependency findOrThrow(Long id) {
        return patchDependencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patch dependency not found with ID: " + id));
    }
}
