package com.example.springsecuritylatest.service;

import com.example.springsecuritylatest.entity.Users;
import com.example.springsecuritylatest.repository.UserRepository;
import com.example.springsecuritylatest.requests.UserRequestDTO;
import com.example.springsecuritylatest.response.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public int save(final UserRequestDTO userRequest){
        final Users user = Users.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(encoder.encode(userRequest.getPassword()))
                .roles(Arrays.stream(userRequest.getRoles().trim().split(",")).map("ROLE_"::concat).map(SimpleGrantedAuthority::new).toList())
                .build();
       return userRepository.save(user).getId();
    }

    public UserResponseDTO getUserById(int id) {
        final Optional<Users> user = userRepository.findById(id);
        return user.map(UserResponseDTO::new).orElseThrow(() -> new RuntimeException("user not found by id : "+id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userInfo = userRepository.findByUsername(username);
        return userInfo.orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
