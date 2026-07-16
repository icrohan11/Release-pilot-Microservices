package com.cts.releasepilot.product.controller;

import com.cts.releasepilot.product.common.Category;
import com.cts.releasepilot.product.common.ProductStatus;
import com.cts.releasepilot.product.dto.ProductRequestDTO;
import com.cts.releasepilot.product.dto.ProductResponseDTO;
import com.cts.releasepilot.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/** Controller test using standalone MockMvc + Mockito (no Spring context / security). */
class ProductControllerTest {

    private MockMvc mockMvc;
    private ProductService productService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        productService = Mockito.mock(ProductService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(productService)).build();
    }

    @Test
    void getAll_returns200() throws Exception {
        when(productService.getAllProducts())
                .thenReturn(List.of(new ProductResponseDTO(), new ProductResponseDTO()));
        mockMvc.perform(get("/products/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void save_returns201() throws Exception {
        ProductRequestDTO req = new ProductRequestDTO();
        req.setProductName("Analytics Suite");
        req.setProductCode("AN-100");
        req.setCategory(Category.SaaS);
        req.setStatus(ProductStatus.Active);

        when(productService.saveProduct(any(ProductRequestDTO.class)))
                .thenReturn(new ProductResponseDTO());

        mockMvc.perform(post("/products/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    void delete_returns200() throws Exception {
        mockMvc.perform(delete("/products/delete/1"))
                .andExpect(status().isOk());
        verify(productService).deleteProduct(1L);
    }
}
