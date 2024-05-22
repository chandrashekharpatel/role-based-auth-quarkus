package com.demo.auth.security;

import io.smallrye.jwt.auth.principal.DefaultJWTCallerPrincipal;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

import com.demo.auth.security.JwtSecurityContext;
import com.demo.auth.security.UserService;

@Provider
@PreMatching
@ApplicationScoped
public class JwtAuthenticationFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Inject
    private JwtHelper jwtHelper;

    @Inject
    private UserService userService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());

            try {
                DefaultJWTCallerPrincipal principal = jwtHelper.validateToken(token);
                String username = principal.getName();
                if (username != null && userService.isUserValid(username)) {
                    requestContext.setSecurityContext(new JwtSecurityContext(username));
                }
            } catch (Exception e) {
                // Handle token validation failure
                // For example, log the error
                e.printStackTrace();
                // You can also throw a specific exception or customize the response
            }
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        // Do any response processing if needed
    }
}
