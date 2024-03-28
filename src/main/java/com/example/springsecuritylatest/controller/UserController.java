package com.example.springsecuritylatest.controller;

import com.example.springsecuritylatest.requests.UserRequestDTO;
import com.example.springsecuritylatest.response.UserResponseDTO;
import com.example.springsecuritylatest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDTO userRequest){
       int userId = userService.save(userRequest);
       return ResponseEntity.ok("User created with id : "+userId);
    }

    @GetMapping("/getUserInfo/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserResponseDTO> getUserInfo(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
