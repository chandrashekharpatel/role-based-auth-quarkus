// package com.demo.auth;


// import io.quarkus.security.AuthenticationRedirectException;
// import io.quarkus.security.ForbiddenException;
// import io.quarkus.security.UnauthorizedException;
// import io.quarkus.security.identity.SecurityIdentity;
// import io.quarkus.security.spi.runtime.AuthorizationController;
// import io.quarkus.vertx.web.Route;
// import io.quarkus.vertx.web.RouteBase;
// import io.quarkus.vertx.web.RouteFilter;
// import io.quarkus.vertx.web.RoutingExchange;
// import org.jboss.logging.Logger;

// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.inject.Inject;

// @ApplicationScoped
// public class SecurityConfigs {

//     @Inject
//     AuthorizationController authorizationController;

//     private static final Logger LOGGER = Logger.getLogger(SecurityConfigs.class);

//     @RouteBase(path = "/")
//     public class MyRoutes {

//         @Route(path = "/test")
//         public void test(RoutingExchange exchange) {
//             SecurityIdentity identity = exchange.getSecurityContext().getIdentity();
//             if (identity == null || !identity.isAuthenticated()) {
//                 throw new UnauthorizedException("You are not authenticated");
//             }
//             // Your logic for /test endpoint
//         }

//         @Route(path = "/auth/login")
//         public void login(RoutingExchange exchange) {
//             // Your logic for /auth/login endpoint
//         }

//         @RouteFilter
//         void authorize(RoutingExchange exchange) {
//             try {
//                 authorizationController.checkPermission(exchange.getSecurityContext().getIdentity(), exchange.getRequestURI());
//                 exchange.next();
//             } catch (ForbiddenException e) {
//                 exchange.end(403);
//             } catch (AuthenticationRedirectException e) {
//                 exchange.redirect(302, e.getLocation());
//             } catch (UnauthorizedException e) {
//                 exchange.end(401);
//             }
//         }
//     }
// }
