package com.todo_management_working.service.impl;

import com.todo_management_working.dto.LoginDto;
import com.todo_management_working.dto.RegisterDto;
import com.todo_management_working.entity.Role;
import com.todo_management_working.exception.TodoAPIException;
import com.todo_management_working.repository.RoleRepository;
import com.todo_management_working.repository.UserRepository;
import com.todo_management_working.service.AuthService;
import lombok.AllArgsConstructor;
import com.todo_management_working.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.HashSet;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterDto registerDto) {

        //check if user already exists

        if(userRepository.existsByUsername(registerDto.getUsername())) {
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        User user = new User();
        user.setName(registerDto.getUsername());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());

        Set<Role> roles = new HashSet<>();

        Role role = roleRepository.findByName("ROLE_USER");
        roles.add(role);

        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public String login(LoginDto loginDto) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken())
        return "";
    }
}
