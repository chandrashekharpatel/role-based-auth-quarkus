// package com.demo.auth;


// public class JwtResponse {
//     private String jwtToken;
//     private String username;

//     // Constructor
//     public JwtResponse(String jwtToken, String username) {
//         this.jwtToken = jwtToken;
//         this.username = username;
//     }

//     // Getters and setters
//     public String getJwtToken() {
//         return jwtToken;
//     }

//     public void setJwtToken(String jwtToken) {
//         this.jwtToken = jwtToken;
//     }

//     public String getUsername() {
//         return username;
//     }

//     public void setUsername(String username) {
//         this.username = username;
//     }

//     // Builder class
//     public static class Builder {
//         private String jwtToken;
//         private String username;

//         public Builder jwtToken(String jwtToken) {
//             this.jwtToken = jwtToken;
//             return this;
//         }

//         public Builder username(String username) {
//             this.username = username;
//             return this;
//         }

//         public JwtResponse build() {
//             return new JwtResponse(jwtToken, username);
//         }
//     }

//     // Builder method
//     public static Builder builder() {
//         return new Builder();
//     }
// }

