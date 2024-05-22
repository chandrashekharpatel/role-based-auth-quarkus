// package com.demo.auth;


// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.enterprise.inject.Produces;

// // import io.quarkus.security.AuthenticationManager;
// // import io.quarkus.security.AuthenticationResponse;
// import io.quarkus.security.identity.SecurityIdentity;
// import io.quarkus.security.runtime.QuarkusPrincipal;
// import io.quarkus.security.runtime.QuarkusSecurityIdentity;

// @ApplicationScoped
// public class MyConfig {

//     @Produces
//     public SecurityIdentity userDetailsService() {
//         return QuarkusSecurityIdentity.builder()
//                 .setPrincipal(new QuarkusPrincipal("chandra"))
//                 .addRole("ADMIN")
//                 .build();
//     }

//     // @Produces
//     // public AuthenticationResponse authenticationManager() {
//     //     return new AuthenticationResponse() {
//     //         @Override
//     //         public SecurityIdentity getIdentity() {
//     //             return userDetailsService();
//     //         }

//     //         @Override
//     //         public void proceed() {
//     //             // No action needed for Quarkus
//     //         }
//     //     };
//     // }
// }