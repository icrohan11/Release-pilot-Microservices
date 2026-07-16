package com.cts.releasepilot.communication.client;

import com.cts.releasepilot.communication.dto.ReleaseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Resilience wrapper around {@link ReleaseClient}. The circuit breaker lives on a
 * dedicated bean (not inside the service impl) so the Spring AOP proxy actually
 * intercepts the call and the fallback engages when release-service is down.
 */
@Component
public class ReleaseClientService {

    private static final Logger log = LoggerFactory.getLogger(ReleaseClientService.class);

    private final ReleaseClient releaseClient;

    public ReleaseClientService(ReleaseClient releaseClient) {
        this.releaseClient = releaseClient;
    }

    @CircuitBreaker(name = "release-service", fallbackMethod = "releaseFallback")
    public ReleaseDTO getRelease(Long releaseID) {
        return releaseClient.getRelease(releaseID);
    }

    /** Fallback used when release-service is unavailable - callers proceed gracefully. */
    public ReleaseDTO releaseFallback(Long releaseID, Throwable ex) {
        log.warn("release-service unavailable for releaseID {} ({}). Using degraded response.",
                releaseID, ex.getMessage());
        return new ReleaseDTO(releaseID, "UNKNOWN", "UNAVAILABLE");
    }
}
