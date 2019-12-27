package com.sid.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullProduct", types = { Product.class })
public interface ProductProjection {

    String getId();

    String getName();

    String getPrice();
}
