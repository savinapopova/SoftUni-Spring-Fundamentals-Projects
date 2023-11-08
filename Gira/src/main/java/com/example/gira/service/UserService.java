package com.example.gira.service;

import com.example.gira.model.dto.UserLoginDTO;
import com.example.gira.model.dto.UserRegisterDTO;

public interface UserService {
    boolean passwordsMatch(UserRegisterDTO userRegisterDTO);

    boolean checkAvailable(UserRegisterDTO userRegisterDTO);

    void register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO);

    boolean isLogged();

    void logout();
}
