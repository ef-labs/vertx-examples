package com.englishtown.vertx.examples.rest;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

/**
 * Response filter to enable CORS
 */
public class CorsResponseFilter implements ContainerResponseFilter {

    public static final String HEADER_ORIGIN = "Origin";
    public static final String HEADER_ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    public static final String HEADER_ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    public static final String HEADER_ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    public static final String HEADER_ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";

    public static final String ALLOW_HEADERS_VALUE = "Content-Type, api_key, Authorization";
    public static final int MAX_AGE_VALUE = 60 * 60; // Cache pre-flight responses for 1h

    /**
     * {@inheritDoc}
     */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        // CORS requests must have an Origin header
        String origin = requestContext.getHeaderString(HEADER_ORIGIN);
        if (origin == null) {
            return;
        }

        // Check if it looks like CORS headers are already set
        String existing = responseContext.getHeaderString(HEADER_ACCESS_CONTROL_ALLOW_ORIGIN);
        if (existing != null && !existing.isEmpty()) {
            return;
        }

        // Always add CORS allow origin header
        responseContext.getHeaders().add(HEADER_ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        // Add some CORS pre-flight headers
        if (HttpMethod.OPTIONS.equalsIgnoreCase(requestContext.getMethod())) {
            responseContext.getHeaders().add(HEADER_ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, DELETE, PUT, PATCH, OPTIONS");
            responseContext.getHeaders().add(HEADER_ACCESS_CONTROL_ALLOW_HEADERS, ALLOW_HEADERS_VALUE);
            responseContext.getHeaders().add(HEADER_ACCESS_CONTROL_MAX_AGE, MAX_AGE_VALUE);
        }

    }

}