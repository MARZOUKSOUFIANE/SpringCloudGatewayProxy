package com.sid.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullCustomer", types = { Customer.class })
public interface CustomerProjection {

    String getId();

    String getName();

    String getEmail();
}
