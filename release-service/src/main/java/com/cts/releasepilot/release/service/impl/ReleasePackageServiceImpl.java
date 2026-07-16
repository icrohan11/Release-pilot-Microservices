package com.cts.releasepilot.release.service.impl;

import com.cts.releasepilot.release.client.ProductClientService;
import com.cts.releasepilot.release.common.ReleaseStatus;
import com.cts.releasepilot.release.dto.ProductDTO;
import com.cts.releasepilot.release.dto.ReleasePackageRequestDTO;
import com.cts.releasepilot.release.dto.ReleasePackageResponseDTO;
import com.cts.releasepilot.release.entity.ReleasePackage;
import com.cts.releasepilot.release.exception.ResourceNotFoundException;
import com.cts.releasepilot.release.mapper.ReleasePackageMapper;
import com.cts.releasepilot.release.repository.ReleasePackageRepository;
import com.cts.releasepilot.release.service.ReleasePackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReleasePackageServiceImpl implements ReleasePackageService {

    private static final Logger log = LoggerFactory.getLogger(ReleasePackageServiceImpl.class);

    private final ReleasePackageRepository releasePackageRepository;
    private final ReleasePackageMapper releasePackageMapper;
    private final ProductClientService productClientService;

    public ReleasePackageServiceImpl(ReleasePackageRepository releasePackageRepository,
                                     ReleasePackageMapper releasePackageMapper,
                                     ProductClientService productClientService) {
        this.releasePackageRepository = releasePackageRepository;
        this.releasePackageMapper = releasePackageMapper;
        this.productClientService = productClientService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReleasePackageResponseDTO> getAll() {
        return releasePackageRepository.findAll().stream().map(releasePackageMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ReleasePackageResponseDTO getById(Long id) {
        return releasePackageMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public ReleasePackageResponseDTO create(ReleasePackageRequestDTO dto) {
        // Validate the referenced product via the product-service (resilient call).
        ProductDTO product = productClientService.getProduct(dto.getProductID());
        log.info("Creating release package for product {} ({})", dto.getProductID(), product.getStatus());

        ReleasePackage entity = releasePackageMapper.toEntity(dto);
        entity.setReleaseID(null);
        if (entity.getStatus() == null) {
            entity.setStatus(ReleaseStatus.Draft);
        }
        return releasePackageMapper.toResponse(releasePackageRepository.save(entity));
    }

    @Override
    @Transactional
    public ReleasePackageResponseDTO update(Long id, ReleasePackageRequestDTO dto) {
        ReleasePackage entity = findOrThrow(id);
        releasePackageMapper.updateEntity(entity, dto);
        return releasePackageMapper.toResponse(releasePackageRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ReleasePackage entity = findOrThrow(id);
        releasePackageRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReleasePackageResponseDTO> getByProduct(Long productID) {
        return releasePackageRepository.findByProductID(productID).stream()
                .map(releasePackageMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReleasePackageResponseDTO> getByStatus(ReleaseStatus status) {
        return releasePackageRepository.findByStatus(status).stream()
                .map(releasePackageMapper::toResponse).toList();
    }

    private ReleasePackage findOrThrow(Long id) {
        return releasePackageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Release package not found with ID: " + id));
    }
}
