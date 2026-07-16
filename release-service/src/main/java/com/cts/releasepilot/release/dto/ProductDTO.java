package com.cts.releasepilot.release.dto;

/** Minimal view of a product fetched from the product-service via Feign. */
public class ProductDTO {

    private Long productID;
    private String productName;
    private String status;

    public ProductDTO() {
    }

    public ProductDTO(Long productID, String productName, String status) {
        this.productID = productID;
        this.productName = productName;
        this.status = status;
    }

    public Long getProductID() { return productID; }
    public void setProductID(Long productID) { this.productID = productID; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
