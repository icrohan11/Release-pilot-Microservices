package com.cts.releasepilot.qa.client;

import com.cts.releasepilot.qa.dto.ReleaseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReleaseClientServiceTest {

    @Test
    void releaseFallback_returnsDegradedResponse() {
        ReleaseClientService svc = new ReleaseClientService(id -> {
            throw new RuntimeException("should not be called");
        });
        ReleaseDTO dto = svc.releaseFallback(1L, new RuntimeException("connection refused"));
        assertEquals(1L, dto.getReleaseID());
        assertEquals("UNAVAILABLE", dto.getStatus());
    }
}
