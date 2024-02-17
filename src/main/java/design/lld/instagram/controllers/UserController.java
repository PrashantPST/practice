package design.lld.instagram.controllers;

import design.lld.instagram.dtos.UserDto;
import design.lld.instagram.dtos.UserRegistrationDto;
import design.lld.instagram.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // Inject the service that handles user registration logic
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        // Validate the registration DTO (Data Transfer Object)
        // You might want to check if the username/email is already in use, etc.

        // Call the service method to handle registration
        UserDto newUser = userService.registerUser(registrationDto);

        // Return the newly created user data and an appropriate HTTP status
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        // Call the service method to handle the deletion
        userService.deleteUser(userId);

        // Return an appropriate HTTP status
        return ResponseEntity.noContent().build();
    }
}

