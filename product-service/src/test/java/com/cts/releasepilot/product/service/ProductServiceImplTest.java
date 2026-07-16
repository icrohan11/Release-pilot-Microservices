package com.cts.releasepilot.product.service;

import com.cts.releasepilot.product.common.Category;
import com.cts.releasepilot.product.common.ProductStatus;
import com.cts.releasepilot.product.dto.ProductRequestDTO;
import com.cts.releasepilot.product.dto.ProductResponseDTO;
import com.cts.releasepilot.product.entity.Product;
import com.cts.releasepilot.product.exception.DuplicateResourceException;
import com.cts.releasepilot.product.exception.ResourceNotFoundException;
import com.cts.releasepilot.product.mapper.ProductMapper;
import com.cts.releasepilot.product.repository.ProductRepository;
import com.cts.releasepilot.product.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock private ProductRepository productRepository;
    @Mock private ProductMapper productMapper;
    @InjectMocks private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(1L, "Analytics Suite", "AN-100", Category.SaaS,
                10L, "1.0.0", ProductStatus.Active);
    }

    @Test
    void getProductById_found() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toResponse(product)).thenReturn(new ProductResponseDTO());
        assertNotNull(productService.getProductById(1L));
    }

    @Test
    void getProductById_notFound_throws() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(99L));
    }

    @Test
    void getAllProducts_returnsMappedList() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        when(productMapper.toResponse(product)).thenReturn(new ProductResponseDTO());
        assertEquals(1, productService.getAllProducts().size());
    }

    @Test
    void saveProduct_success() {
        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setProductName("Analytics Suite");
        dto.setProductCode("AN-100");
        dto.setCategory(Category.SaaS);
        dto.setStatus(ProductStatus.Active);

        when(productRepository.existsByProductCode("AN-100")).thenReturn(false);
        when(productMapper.toEntity(dto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toResponse(product)).thenReturn(new ProductResponseDTO());

        assertNotNull(productService.saveProduct(dto));
        verify(productRepository).save(product);
    }

    @Test
    void saveProduct_duplicateCode_throws() {
        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setProductCode("AN-100");

        when(productRepository.existsByProductCode("AN-100")).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> productService.saveProduct(dto));
        verify(productRepository, never()).save(any());
    }

    @Test
    void deleteProduct_notFound_throws() {
        when(productRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(5L));
        verify(productRepository, never()).delete(any());
    }
}
