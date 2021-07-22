package com.example.gistcompetitioncnserver.user;

import com.example.gistcompetitioncnserver.registration.token.EmailConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailConfirmationToken emailConfirmationToken;

}
