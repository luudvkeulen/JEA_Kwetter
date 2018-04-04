package rest;

import domain.User;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import static javax.ws.rs.core.MediaType.*;
import javax.ws.rs.core.Response;
import service.LoginService;

@Path("login")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Stateless
public class LoginResource {

    @Inject
    private LoginService loginService;
    
    @POST
    public Response login(User user) {
        boolean valid = loginService.authenticate(user.getUsername(),user.getPassword());

        System.out.println("Username " + user.getUsername());
        System.out.println("Password " + user.getPassword());
        
        if (valid) {
            String token = loginService.issueToken(user.getUsername());
            return Response.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .header("Access-Control-Expose-Headers", "Authorization")
                    .build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
