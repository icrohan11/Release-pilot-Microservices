package com.cts.releasepilot.backlog.service.impl;

import com.cts.releasepilot.backlog.common.SprintItemStatus;
import com.cts.releasepilot.backlog.dto.SprintItemRequestDTO;
import com.cts.releasepilot.backlog.dto.SprintItemResponseDTO;
import com.cts.releasepilot.backlog.entity.SprintItem;
import com.cts.releasepilot.backlog.exception.ResourceNotFoundException;
import com.cts.releasepilot.backlog.mapper.SprintItemMapper;
import com.cts.releasepilot.backlog.repository.SprintItemRepository;
import com.cts.releasepilot.backlog.service.SprintItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SprintItemServiceImpl implements SprintItemService {

    private final SprintItemRepository sprintItemRepository;
    private final SprintItemMapper sprintItemMapper;

    public SprintItemServiceImpl(SprintItemRepository sprintItemRepository, SprintItemMapper sprintItemMapper) {
        this.sprintItemRepository = sprintItemRepository;
        this.sprintItemMapper = sprintItemMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SprintItemResponseDTO> getAllSprintItems() {
        return sprintItemRepository.findAll().stream().map(sprintItemMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SprintItemResponseDTO getSprintItemById(Long id) {
        return sprintItemMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public SprintItemResponseDTO createSprintItem(SprintItemRequestDTO dto) {
        SprintItem item = sprintItemMapper.toEntity(dto);
        if (item.getStatus() == null) {
            item.setStatus(SprintItemStatus.ToDo);
        }
        return sprintItemMapper.toResponse(sprintItemRepository.save(item));
    }

    @Override
    @Transactional
    public SprintItemResponseDTO updateSprintItem(Long id, SprintItemRequestDTO dto) {
        SprintItem item = findOrThrow(id);
        sprintItemMapper.updateEntity(item, dto);
        return sprintItemMapper.toResponse(sprintItemRepository.save(item));
    }

    @Override
    @Transactional
    public void deleteSprintItem(Long id) {
        SprintItem item = findOrThrow(id);
        sprintItemRepository.delete(item);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SprintItemResponseDTO> getSprintItemsBySprint(Long sprintID) {
        return sprintItemRepository.findBySprintID(sprintID).stream()
                .map(sprintItemMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SprintItemResponseDTO> getSprintItemsByStatus(SprintItemStatus status) {
        return sprintItemRepository.findByStatus(status).stream()
                .map(sprintItemMapper::toResponse).toList();
    }

    private SprintItem findOrThrow(Long id) {
        return sprintItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint item not found with ID: " + id));
    }
}
