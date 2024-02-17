package design.lld.youtube.service;

import design.lld.youtube.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  public User authenticateUser(Authentication authentication) {
    if (authentication == null) {
      throw new SecurityException("No authentication information provided");
    }

    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetails userDetails) {
      return findUserByUsername(userDetails.getUsername());
    } else {
      throw new SecurityException("Authentication principal is not of expected type");
    }
  }

  private User findUserByUsername(String username) {
    // Here, interact with your user service or repository to retrieve the User object.
    // This is a placeholder implementation.
    // For example:
    // return userRepository.findByUsername(username);
    return new User(username); // Assuming a User class that has a constructor with username
  }
}
