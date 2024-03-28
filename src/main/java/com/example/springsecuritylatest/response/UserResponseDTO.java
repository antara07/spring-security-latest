package com.example.springsecuritylatest.response;

import com.example.springsecuritylatest.entity.Users;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private int id;
    private String username;
    private String email;
    private String roles;

    public UserResponseDTO(Users users) {
        id=users.getId();
        username=users.getUsername();
        email=users.getEmail();
        roles = users.getRoles().toString();
    }
}
