package com.cts.releasepilot.product.service.impl;

import com.cts.releasepilot.product.common.Category;
import com.cts.releasepilot.product.common.ProductStatus;
import com.cts.releasepilot.product.dto.ProductRequestDTO;
import com.cts.releasepilot.product.dto.ProductResponseDTO;
import com.cts.releasepilot.product.entity.Product;
import com.cts.releasepilot.product.exception.DuplicateResourceException;
import com.cts.releasepilot.product.exception.ResourceNotFoundException;
import com.cts.releasepilot.product.mapper.ProductMapper;
import com.cts.releasepilot.product.repository.ProductRepository;
import com.cts.releasepilot.product.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO getProductById(Long id) {
        return productMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public ProductResponseDTO saveProduct(ProductRequestDTO dto) {
        if (productRepository.existsByProductCode(dto.getProductCode())) {
            throw new DuplicateResourceException("Product code already in use: " + dto.getProductCode());
        }
        Product product = productMapper.toEntity(dto);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        Product product = findOrThrow(id);
        // Block changing the code to one already used by a different product
        productRepository.findByProductCode(dto.getProductCode())
                .filter(other -> !other.getProductID().equals(id))
                .ifPresent(other -> {
                    throw new DuplicateResourceException("Product code already in use: " + dto.getProductCode());
                });
        productMapper.updateEntity(product, dto);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = findOrThrow(id);
        productRepository.delete(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getProductsByStatus(ProductStatus status) {
        return productRepository.findByStatus(status).stream().map(productMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category).stream().map(productMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getProductsByOwner(Long ownerID) {
        return productRepository.findByOwnerID(ownerID).stream().map(productMapper::toResponse).toList();
    }

    private Product findOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    }
}
