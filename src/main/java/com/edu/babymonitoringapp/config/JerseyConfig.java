package com.edu.babymonitoringapp.config;

import com.edu.babymonitoringapp.controller.RestResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
    register(SecurityFilter.class);
    register(RestResource.class);
    }
}
