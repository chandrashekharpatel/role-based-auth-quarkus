package com.demo.auth.security;

import io.smallrye.jwt.auth.principal.DefaultJWTCallerPrincipal;

import jakarta.ws.rs.core.SecurityContext;
import java.security.Principal;

public class JwtSecurityContext implements SecurityContext {

    private final String username;

    public JwtSecurityContext(String username) {
        this.username = username;
    }

    @Override
    public Principal getUserPrincipal() {
        return () -> username;
    }

    @Override
    public boolean isUserInRole(String role) {
        DefaultJWTCallerPrincipal jwtPrincipal = (DefaultJWTCallerPrincipal) getUserPrincipal();
        return jwtPrincipal.getGroups().contains(role);
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public String getAuthenticationScheme() {
        return "JWT";
    }
}
