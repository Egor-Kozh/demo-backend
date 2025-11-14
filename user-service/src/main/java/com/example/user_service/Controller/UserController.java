package com.example.user_service.Controller;

import com.example.user_service.Model.Dto.UserDto;
import com.example.user_service.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto newUser) {
        return userService.createUser(newUser);
    }
}
