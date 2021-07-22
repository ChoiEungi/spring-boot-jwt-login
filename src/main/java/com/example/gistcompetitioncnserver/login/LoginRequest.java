package com.example.gistcompetitioncnserver.login;

import com.example.gistcompetitioncnserver.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequest {
    private final String email;
    private final String password;

}
