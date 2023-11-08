package com.example.andreys.service;

import com.example.andreys.model.dto.UserLoginDTO;
import com.example.andreys.model.dto.UserRegisterDTO;

public interface UserService {
    boolean passwordsMatch(UserRegisterDTO userRegisterDTO);

    boolean checkAvailable(UserRegisterDTO userRegisterDTO);

    void register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();

    boolean isLogged();
}
