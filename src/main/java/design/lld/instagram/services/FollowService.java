package design.lld.instagram.services;

import design.lld.instagram.dtos.UserDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

  public void follow(String followingId, String followerId) {

  }

  public void unfollow(String followingId, String followerId) {

  }

  public List<UserDto> getFollowers(String followingId) {
    return null;
  }

  public List<UserDto> getFollowings(String followerId) {
    return null;
  }
}