package com.cts.releasepilot.release.client;

import com.cts.releasepilot.release.config.FeignClientConfig;
import com.cts.releasepilot.release.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/** Feign client for the product-service, used to validate referenced products. */
@FeignClient(name = "product-service", configuration = FeignClientConfig.class)
public interface ProductClient {

    @GetMapping("/products/view/{id}")
    ProductDTO getProduct(@PathVariable("id") Long id);
}
