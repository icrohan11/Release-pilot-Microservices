package com.cts.releasepilot.release.dto;

import com.cts.releasepilot.release.common.EnvName;
import com.cts.releasepilot.release.common.EnvStatus;

import java.time.LocalDateTime;

/** Environment representation returned to clients. */
public class EnvironmentResponseDTO {

    private Long envID;
    private Long productID;
    private EnvName envName;
    private Long ownerID;
    private String currentVersion;
    private LocalDateTime lastPromotionDate;
    private EnvStatus status;

    public Long getEnvID() { return envID; }
    public void setEnvID(Long envID) { this.envID = envID; }

    public Long getProductID() { return productID; }
    public void setProductID(Long productID) { this.productID = productID; }

    public EnvName getEnvName() { return envName; }
    public void setEnvName(EnvName envName) { this.envName = envName; }

    public Long getOwnerID() { return ownerID; }
    public void setOwnerID(Long ownerID) { this.ownerID = ownerID; }

    public String getCurrentVersion() { return currentVersion; }
    public void setCurrentVersion(String currentVersion) { this.currentVersion = currentVersion; }

    public LocalDateTime getLastPromotionDate() { return lastPromotionDate; }
    public void setLastPromotionDate(LocalDateTime lastPromotionDate) { this.lastPromotionDate = lastPromotionDate; }

    public EnvStatus getStatus() { return status; }
    public void setStatus(EnvStatus status) { this.status = status; }
}
