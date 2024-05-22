package com.demo.auth.security;

import io.smallrye.jwt.auth.principal.DefaultJWTCallerPrincipal;
import io.smallrye.jwt.build.Jwt;

import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

import jakarta.enterprise.context.ApplicationScoped;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

@ApplicationScoped
public class JwtHelper {

    private static final String ISSUER = "http://localhost:8083/issuer";
    private static final String AUDIENCE = "secure-api";

    private PrivateKey privateKey; // Initialize this key

    public JwtHelper() {
        // Initialize the key
        KeyPair keyPair = generateKeyPair();
        privateKey = keyPair.getPrivate();
    }

    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // Key size
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate RSA key pair", e);
        }
    }

    public String generateToken(String username, String password) {
        long currentTimeInSecs = System.currentTimeMillis() / 1000;
        long exp = currentTimeInSecs + 3600; // Token expires in 1 hour
    
        return Jwt.issuer(ISSUER)
                .subject(username) // Set the username as the subject
                .groups(password) // Set the roles directly
                .expiresAt(Instant.ofEpochSecond(exp)) // Set the expiration time
                .audience(AUDIENCE) // Set the audience
                .sign(privateKey); // Sign the token using the private key
    }
    
 

    public DefaultJWTCallerPrincipal validateToken(String token) {
        try {
            return (DefaultJWTCallerPrincipal) new JwtConsumerBuilder()
                    .build()
                    .processToClaims(token)
                    .getClaimValue(token, null);
        } catch (Exception e) {
            // Handle validation failure
            return null;
        }
    }
    
    
}    
