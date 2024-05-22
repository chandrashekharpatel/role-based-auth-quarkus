// package com.demo.auth;

// import java.io.IOException;
// import java.io.PrintWriter;

// import javax.naming.AuthenticationException;

// import jakarta.inject.Singleton;
// import jakarta.ws.rs.core.Context;
// import jakarta.ws.rs.core.HttpHeaders;
// import jakarta.ws.rs.core.Response;
// import jakarta.ws.rs.ext.ExceptionMapper;
// import jakarta.ws.rs.ext.Provider;

// @Provider
// @Singleton
// public class JwtAuthenticationExceptionMapper implements ExceptionMapper<Exception> {

//     @Override
//     public Response toResponse(Exception exception) {
//         if (exception instanceof AuthenticationException) {
//             return Response.status(Response.Status.UNAUTHORIZED)
//                     .entity("Access Denied !! " + exception.getMessage())
//                     .build();
//         } else {
//             return Response.serverError().entity("Internal Server Error").build();
//         }
//     }
// }
