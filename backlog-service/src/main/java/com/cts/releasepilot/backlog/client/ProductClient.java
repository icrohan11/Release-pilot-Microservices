package com.cts.releasepilot.backlog.client;

import com.cts.releasepilot.backlog.config.FeignClientConfig;
import com.cts.releasepilot.backlog.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/** Feign client for cross-service calls to the product-service. */
@FeignClient(name = "product-service", configuration = FeignClientConfig.class)
public interface ProductClient {

    @GetMapping("/products/view/{id}")
    ProductDTO getProduct(@PathVariable("id") Long id);
}
