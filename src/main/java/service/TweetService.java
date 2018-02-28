package service;

import dao.UserDAO;
import domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;

public class TweetService {

    @Inject
    private UserDAO userDAO;

    private List<User> findMentions(String message) {
        List<User> mentions = new ArrayList<>();

        String prefixedString = " ".concat(message);
        Matcher m = Pattern.compile("(?:\\s#)([A-Za-z0-9_]+)").matcher(prefixedString);
        while (m.find()) {
            List<User> users = userDAO.findByUsername(m.group(1));
            if (users.size() > 0) {
                mentions.add(users.get(0));
            }
        }

        return mentions;
    }
}
