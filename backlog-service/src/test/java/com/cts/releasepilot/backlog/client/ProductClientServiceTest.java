package com.cts.releasepilot.backlog.client;

import com.cts.releasepilot.backlog.dto.ProductDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductClientServiceTest {

    @Test
    void productFallback_returnsDegradedResponse() {
        ProductClientService svc = new ProductClientService(id -> {
            throw new RuntimeException("should not be called");
        });
        ProductDTO dto = svc.productFallback(10L, new RuntimeException("connection refused"));
        assertEquals(10L, dto.getProductID());
        assertEquals("UNKNOWN", dto.getStatus());
    }
}
