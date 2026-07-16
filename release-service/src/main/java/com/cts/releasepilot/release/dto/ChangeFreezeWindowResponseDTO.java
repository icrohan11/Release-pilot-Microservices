package com.cts.releasepilot.release.dto;

import com.cts.releasepilot.release.common.FreezeStatus;

import java.time.LocalDate;

/** Change freeze window representation returned to clients. */
public class ChangeFreezeWindowResponseDTO {

    private Long freezeID;
    private Long productID;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private Long approvedByID;
    private FreezeStatus status;

    public Long getFreezeID() { return freezeID; }
    public void setFreezeID(Long freezeID) { this.freezeID = freezeID; }

    public Long getProductID() { return productID; }
    public void setProductID(Long productID) { this.productID = productID; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Long getApprovedByID() { return approvedByID; }
    public void setApprovedByID(Long approvedByID) { this.approvedByID = approvedByID; }

    public FreezeStatus getStatus() { return status; }
    public void setStatus(FreezeStatus status) { this.status = status; }
}
