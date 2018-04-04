package service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dao.UserDAO;
import domain.User;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.codec.digest.DigestUtils;

@Stateless
public class LoginService {

    @Inject
    private UserDAO userDAO;
    
    public boolean authenticate(String username, String password) {
        List<User> users = userDAO.findByUsername(username);
        if (users.isEmpty()) {
            return false;
        }

        User user = users.get(0);

        //String hashedPw = DigestUtils.sha512Hex(password);
        return password.equals(user.getPassword());
    }

    public String issueToken(String login) {
        Algorithm algorithm;
        String token = "";
        try {
            algorithm = Algorithm.HMAC512("supersecret");
            token = JWT.create().withSubject(login).withIssuer("Luud").sign(algorithm);
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
        }

        return token;
    }
}
