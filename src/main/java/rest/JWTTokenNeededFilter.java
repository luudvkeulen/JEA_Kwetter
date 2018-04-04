package rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        String authorizationHeader = crc.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader == null || authorizationHeader.length() == 0) {
            crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {
            Algorithm algorithm = Algorithm.HMAC512("supersecret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Luud")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException | UnsupportedEncodingException | IllegalArgumentException e) {
            crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

}
