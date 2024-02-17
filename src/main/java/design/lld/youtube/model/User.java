package design.lld.youtube.model;

public class User {
  private String username;
  private String password; // Store encrypted passwords only
  private String email;
  // Other fields like firstName, lastName, etc.

  // Constructor
  public User(String username) {
    this.username = username;
  }

  // Getters and Setters
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  // Other getters and setters...
}

