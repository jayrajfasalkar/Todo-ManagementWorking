package com.todo_management_working.service;

import com.todo_management_working.dto.LoginDto;
import com.todo_management_working.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
