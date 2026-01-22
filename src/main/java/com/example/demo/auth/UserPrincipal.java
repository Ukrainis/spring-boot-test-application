package com.example.demo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPrincipal {

    private long id;

    private String userName;
}
