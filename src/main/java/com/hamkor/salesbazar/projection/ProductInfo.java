package com.hamkor.salesbazar.projection;

/**
 * Projection for {@link com.hamkor.salesbazar.entity.Product}
 */
public interface ProductInfo {
    Long getId();

    String getName();

    ProductTypeInfo getProductType();
}