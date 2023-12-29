package com.hamkor.salesbazar.projection;

/**
 * Projection for {@link com.hamkor.salesbazar.entity.HamkorClient}
 */
public interface HamkorClientInfo {
    Long getId();

    String getFirstname();

    String getLastname();

    String getPhone();

    String getBusinessName();
}