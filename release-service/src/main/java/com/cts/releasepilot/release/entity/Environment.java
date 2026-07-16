package com.cts.releasepilot.release.entity;

import com.cts.releasepilot.release.common.EnvName;
import com.cts.releasepilot.release.common.EnvStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/** A deployment environment for a product (Dev, SIT, UAT, PreProd, Production). */
@Entity
@Table(name = "environments")
public class Environment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long envID;

    private Long productID;

    @Enumerated(EnumType.STRING)
    private EnvName envName;

    private Long ownerID;

    private String currentVersion;

    private LocalDateTime lastPromotionDate;

    @Enumerated(EnumType.STRING)
    private EnvStatus status;

    public Environment() {
    }

    public Environment(Long envID, Long productID, EnvName envName, Long ownerID,
                       String currentVersion, LocalDateTime lastPromotionDate, EnvStatus status) {
        this.envID = envID;
        this.productID = productID;
        this.envName = envName;
        this.ownerID = ownerID;
        this.currentVersion = currentVersion;
        this.lastPromotionDate = lastPromotionDate;
        this.status = status;
    }

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
