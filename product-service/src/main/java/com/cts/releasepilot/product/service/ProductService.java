package com.cts.releasepilot.product.service;

import com.cts.releasepilot.product.common.Category;
import com.cts.releasepilot.product.common.ProductStatus;
import com.cts.releasepilot.product.dto.ProductRequestDTO;
import com.cts.releasepilot.product.dto.ProductResponseDTO;

import java.util.List;

/** Product catalog operations. */
public interface ProductService {

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProductById(Long id);

    ProductResponseDTO saveProduct(ProductRequestDTO dto);

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto);

    void deleteProduct(Long id);

    List<ProductResponseDTO> getProductsByStatus(ProductStatus status);

    List<ProductResponseDTO> getProductsByCategory(Category category);

    List<ProductResponseDTO> getProductsByOwner(Long ownerID);
}
