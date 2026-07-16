package com.cts.releasepilot.communication.client;

import com.cts.releasepilot.communication.config.FeignClientConfig;
import com.cts.releasepilot.communication.dto.ReleaseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/** Feign client to the release-service, used to validate releases before publishing notes. */
@FeignClient(name = "release-service", configuration = FeignClientConfig.class)
public interface ReleaseClient {

    @GetMapping("/releases/view/{id}")
    ReleaseDTO getRelease(@PathVariable("id") Long id);
}
