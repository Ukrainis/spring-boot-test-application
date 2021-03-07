package com.example.demo.interfaces;

import com.example.demo.auth.UserPrincipal;
import com.example.demo.entities.User;

import java.time.LocalDate;

public interface TokenService {

    String generateToken(User user);

    UserPrincipal parseToken(String token);
}
