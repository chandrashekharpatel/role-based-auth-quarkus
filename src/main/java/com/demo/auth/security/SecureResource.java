package com.demo.auth.security;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/secured")
public class SecureResource {

    @Inject
    SecurityIdentity securityIdentity;
    @Inject
    UserService userService;
    @Inject
    JwtHelper jwtHelper;

    @GET
    @Authenticated
    @Path("/protected")
    public Response getProtectedResource() {
        String username = securityIdentity.getPrincipal().getName();
        return Response.ok("Hello, " + username + "! This is a protected resource.").build();
    }

    @GET
    @Path("/public")
    public Response getPublicResource() {
        return Response.ok("This is a public resource.").build();
    }
     @GET
    @Path("/admin")
    @PermitAll
    public Response adminResource(@Context SecurityContext securityContext) {
        // Check if the user has the "admin" role
        if (securityContext.isUserInRole("admin")) {
            // Authorization logic for admin resource
            return Response.ok("Admin resource accessed").build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).entity("Access denied").build();
        }
    }
     @GET
    @Path("/admin")
    @RolesAllowed("admin") // Only users with the "admin" role can access this endpoint
    public Response adminResource() {
        // Authorization logic for admin resource
        return Response.ok("Admin resource accessed").build();
    }

    @GET
    @Path("/user")
    @RolesAllowed({"admin", "user"}) // Users with "admin" or "user" role can access this endpoint
    public Response userResource() {
        // Authorization logic for user resource
        return Response.ok("User resource accessed").build();
    }
      @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User request) {
        // Perform authentication
        if (!userService.isValidCredentials(request.getUsername(), request.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid username or password").build();
        }

        // Generate JWT token
        String token = jwtHelper.generateToken(request.getUsername(), request.getPassword());

        // Return token as response
        return Response.ok().entity(token).build();
    }
}

