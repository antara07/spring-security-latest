package com.example.springsecuritylatest.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String username;
    private String email;
    private String password;
    private String roles;
}
