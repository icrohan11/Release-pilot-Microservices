package com.cts.releasepilot.product.entity;

import com.cts.releasepilot.product.common.Category;
import com.cts.releasepilot.product.common.ProductStatus;
import jakarta.persistence.*;

/** Persistent product catalog entry. */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    private String productName;

    @Column(unique = true, nullable = false)
    private String productCode;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Long ownerID;

    private String currentVersion;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    public Product() {
    }

    public Product(Long productID, String productName, String productCode, Category category,
                   Long ownerID, String currentVersion, ProductStatus status) {
        this.productID = productID;
        this.productName = productName;
        this.productCode = productCode;
        this.category = category;
        this.ownerID = ownerID;
        this.currentVersion = currentVersion;
        this.status = status;
    }

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
