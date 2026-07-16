package com.cts.releasepilot.qa.client;

import com.cts.releasepilot.qa.dto.ReleaseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/** Feign client for the release-service, used to validate releases referenced by executions. */
@FeignClient(name = "release-service", configuration = com.cts.releasepilot.qa.config.FeignClientConfig.class)
public interface ReleaseClient {

    @GetMapping("/releases/view/{id}")
    ReleaseDTO getRelease(@PathVariable("id") Long id);
}
