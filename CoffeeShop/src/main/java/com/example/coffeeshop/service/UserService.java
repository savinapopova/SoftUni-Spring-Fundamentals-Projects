package com.example.coffeeshop.service;

import com.example.coffeeshop.model.dto.UserDTO;
import com.example.coffeeshop.model.dto.UserLoginDTO;
import com.example.coffeeshop.model.dto.UserRegisterDTO;
import com.example.coffeeshop.model.entity.User;

import java.util.List;

public interface UserService {
    boolean passwordsMatch(UserRegisterDTO userRegisterDTO);

    boolean checkAvailable(UserRegisterDTO userRegisterDTO);

    void register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO);

    User getLoggedUser();

    List<UserDTO> getAllUsers();

    void logout();

    boolean isLogged();
}
