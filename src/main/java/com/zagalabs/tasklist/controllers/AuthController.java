package com.zagalabs.tasklist.controllers;

import com.zagalabs.tasklist.models.LoginUserRequest;
import com.zagalabs.tasklist.models.LoginUserResponse;
import com.zagalabs.tasklist.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(path = "/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    @CrossOrigin
    public @ResponseBody LoginUserResponse loginUser(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        final String token = authService
                .validateAndCreateSessionForUser(loginUserRequest.getUserId(), loginUserRequest.getPassword());

        final LoginUserResponse loginUserResponse = new LoginUserResponse();
        loginUserResponse.setToken(token);
        loginUserResponse.setGenerationDate(new Date());

        return loginUserResponse;
    }

}
