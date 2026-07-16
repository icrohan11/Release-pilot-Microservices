package com.cts.releasepilot.product.dto;

import com.cts.releasepilot.product.common.Category;
import com.cts.releasepilot.product.common.ProductStatus;

/** Product representation returned to clients. */
public class ProductResponseDTO {

    private Long productID;
    private String productName;
    private String productCode;
    private Category category;
    private Long ownerID;
    private String currentVersion;
    private ProductStatus status;

    public Long getProductID() { return productID; }
    public void setProductID(Long productID) { this.productID = productID; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Long getOwnerID() { return ownerID; }
    public void setOwnerID(Long ownerID) { this.ownerID = ownerID; }

    public String getCurrentVersion() { return currentVersion; }
    public void setCurrentVersion(String currentVersion) { this.currentVersion = currentVersion; }

    public ProductStatus getStatus() { return status; }
    public void setStatus(ProductStatus status) { this.status = status; }
}
