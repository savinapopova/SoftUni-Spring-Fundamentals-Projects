package com.example.musicdb.service;

import com.example.musicdb.model.dto.UserLoginDTO;
import com.example.musicdb.model.dto.UserRegisterDTO;

public interface UserService {
    boolean passwordsMatch(UserRegisterDTO userRegisterDTO);

    boolean checkAvailable(UserRegisterDTO userRegisterDTO);

    void register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO);

    boolean isLogged();

    void logout();
}
