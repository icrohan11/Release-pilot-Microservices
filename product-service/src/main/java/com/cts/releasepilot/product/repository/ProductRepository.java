package com.cts.releasepilot.product.repository;

import com.cts.releasepilot.product.common.Category;
import com.cts.releasepilot.product.common.ProductStatus;
import com.cts.releasepilot.product.entity.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Product repository. Extends only what is needed: CRUD ({@link ListCrudRepository})
 * plus pagination/sorting for product listings - no JpaRepository-specific methods
 * (flush, batch) are required here.
 */
@Repository
public interface ProductRepository extends ListCrudRepository<Product, Long>,
        PagingAndSortingRepository<Product, Long> {

    List<Product> findByStatus(ProductStatus status);

    List<Product> findByCategory(Category category);

    List<Product> findByOwnerID(Long ownerID);

    boolean existsByProductCode(String productCode);

    Optional<Product> findByProductCode(String productCode);
}
