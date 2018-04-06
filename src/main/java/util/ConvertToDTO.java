package util;

import domain.Tweet;
import domain.User;
import dto.TweetDTO;
import dto.UserDTO;
import java.util.ArrayList;
import java.util.List;

public class ConvertToDTO {
    public static List<UserDTO> USERSTODTOS(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        if (users == null || users.isEmpty()) {
            return userDTOs;
        }

        for (User u : users) {
            UserDTO user = new UserDTO(
                    u.getId(),
                    u.getUsername(),
                    u.getFirstName(),
                    u.getLastName(),
                    u.getPicture(),
                    u.getWebsite(),
                    u.getBio(),
                    u.getLocation(),
                    u.getEmail(),
                    u.getTweets().size(),
                    u.getFollowing().size(),
                    u.getFollowers().size());

            userDTOs.add(user);
        }

        return userDTOs;
    }

    public static List<TweetDTO> TWEETSTODTOS(List<Tweet> tweets) {
        List<TweetDTO> tweetDTOs = new ArrayList<>();
        if (tweets == null || tweets.isEmpty()) {
            return tweetDTOs;
        }

        for (Tweet t : tweets) {
            TweetDTO tweetDTO = new TweetDTO(
                    t.getId(),
                    t.getTweetedBy().getUsername(),
                    t.getMessage(),
                    t.getPublished(),
                    t.getTags(),
                    t.getLikes(),
                    t.getMentions());
            tweetDTOs.add(tweetDTO);
        }

        return tweetDTOs;
    }
}
