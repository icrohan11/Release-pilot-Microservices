package com.cts.releasepilot.release.client;

import com.cts.releasepilot.release.dto.ProductDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Resilience wrapper around {@link ProductClient}. The circuit breaker lives on a
 * dedicated bean (not inside the service impl) so the Spring AOP proxy actually
 * intercepts the call and the fallback engages when product-service is down.
 */
@Component
public class ProductClientService {

    private static final Logger log = LoggerFactory.getLogger(ProductClientService.class);

    private final ProductClient productClient;

    public ProductClientService(ProductClient productClient) {
        this.productClient = productClient;
    }

    @CircuitBreaker(name = "product-service", fallbackMethod = "productFallback")
    public ProductDTO getProduct(Long productID) {
        return productClient.getProduct(productID);
    }

    /** Fallback used when product-service is unavailable - callers proceed gracefully. */
    public ProductDTO productFallback(Long productID, Throwable ex) {
        log.warn("product-service unavailable for productID {} ({}). Using degraded response.",
                productID, ex.getMessage());
        return new ProductDTO(productID, "Unavailable", "UNKNOWN");
    }
}
