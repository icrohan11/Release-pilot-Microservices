package com.cts.releasepilot.backlog.service.impl;

import com.cts.releasepilot.backlog.client.ProductClientService;
import com.cts.releasepilot.backlog.common.BacklogStatus;
import com.cts.releasepilot.backlog.common.BacklogType;
import com.cts.releasepilot.backlog.common.Priority;
import com.cts.releasepilot.backlog.dto.BacklogItemRequestDTO;
import com.cts.releasepilot.backlog.dto.BacklogItemResponseDTO;
import com.cts.releasepilot.backlog.dto.ProductDTO;
import com.cts.releasepilot.backlog.entity.BacklogItem;
import com.cts.releasepilot.backlog.exception.ResourceNotFoundException;
import com.cts.releasepilot.backlog.mapper.BacklogItemMapper;
import com.cts.releasepilot.backlog.repository.BacklogItemRepository;
import com.cts.releasepilot.backlog.service.BacklogItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BacklogItemServiceImpl implements BacklogItemService {

    private static final Logger log = LoggerFactory.getLogger(BacklogItemServiceImpl.class);

    private final BacklogItemRepository backlogItemRepository;
    private final BacklogItemMapper backlogItemMapper;
    private final ProductClientService productClientService;

    public BacklogItemServiceImpl(BacklogItemRepository backlogItemRepository,
                                  BacklogItemMapper backlogItemMapper,
                                  ProductClientService productClientService) {
        this.backlogItemRepository = backlogItemRepository;
        this.backlogItemMapper = backlogItemMapper;
        this.productClientService = productClientService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BacklogItemResponseDTO> getAllBacklogItems() {
        return backlogItemRepository.findAll().stream().map(backlogItemMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BacklogItemResponseDTO getBacklogItemById(Long id) {
        return backlogItemMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public BacklogItemResponseDTO createBacklogItem(BacklogItemRequestDTO dto) {
        // Validate the referenced product via the product-service (resilience-guarded).
        ProductDTO product = productClientService.getProduct(dto.getProductID());
        log.info("Creating backlog item for product '{}' (status={})",
                product.getProductName(), product.getStatus());

        BacklogItem item = backlogItemMapper.toEntity(dto);
        if (item.getStatus() == null) {
            item.setStatus(BacklogStatus.New);
        }
        return backlogItemMapper.toResponse(backlogItemRepository.save(item));
    }

    @Override
    @Transactional
    public BacklogItemResponseDTO updateBacklogItem(Long id, BacklogItemRequestDTO dto) {
        BacklogItem item = findOrThrow(id);
        backlogItemMapper.updateEntity(item, dto);
        return backlogItemMapper.toResponse(backlogItemRepository.save(item));
    }

    @Override
    @Transactional
    public void deleteBacklogItem(Long id) {
        BacklogItem item = findOrThrow(id);
        backlogItemRepository.delete(item);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BacklogItemResponseDTO> getBacklogItemsByProduct(Long productID) {
        return backlogItemRepository.findByProductID(productID).stream()
                .map(backlogItemMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BacklogItemResponseDTO> getBacklogItemsByStatus(BacklogStatus status) {
        return backlogItemRepository.findByStatus(status).stream()
                .map(backlogItemMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BacklogItemResponseDTO> getBacklogItemsByPriority(Priority priority) {
        return backlogItemRepository.findByPriority(priority).stream()
                .map(backlogItemMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BacklogItemResponseDTO> getBacklogItemsByType(BacklogType type) {
        return backlogItemRepository.findByType(type).stream()
                .map(backlogItemMapper::toResponse).toList();
    }

    private BacklogItem findOrThrow(Long id) {
        return backlogItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Backlog item not found with ID: " + id));
    }
}
