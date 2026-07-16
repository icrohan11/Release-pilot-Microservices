package com.cts.releasepilot.product.mapper;

import com.cts.releasepilot.product.dto.ProductRequestDTO;
import com.cts.releasepilot.product.dto.ProductResponseDTO;
import com.cts.releasepilot.product.entity.Product;
import org.springframework.stereotype.Component;

/** Converts between {@link Product} entities and their DTOs. */
@Component
public class ProductMapper {

    /** Build a new entity from a request DTO. */
    public Product toEntity(ProductRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setProductCode(dto.getProductCode());
        product.setCategory(dto.getCategory());
        product.setOwnerID(dto.getOwnerID());
        product.setCurrentVersion(dto.getCurrentVersion());
        product.setStatus(dto.getStatus());
        return product;
    }

    /** Apply an update DTO onto an existing entity. */
    public void updateEntity(Product product, ProductRequestDTO dto) {
        product.setProductName(dto.getProductName());
        product.setProductCode(dto.getProductCode());
        product.setCategory(dto.getCategory());
        product.setOwnerID(dto.getOwnerID());
        product.setCurrentVersion(dto.getCurrentVersion());
        product.setStatus(dto.getStatus());
    }

    public ProductResponseDTO toResponse(Product product) {
        if (product == null) {
            return null;
        }
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductID(product.getProductID());
        dto.setProductName(product.getProductName());
        dto.setProductCode(product.getProductCode());
        dto.setCategory(product.getCategory());
        dto.setOwnerID(product.getOwnerID());
        dto.setCurrentVersion(product.getCurrentVersion());
        dto.setStatus(product.getStatus());
        return dto;
    }
}
