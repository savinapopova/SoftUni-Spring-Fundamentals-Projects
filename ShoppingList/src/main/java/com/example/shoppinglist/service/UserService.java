package com.example.shoppinglist.service;

import com.example.shoppinglist.model.dto.UserLoginDTO;
import com.example.shoppinglist.model.dto.UserRegisterDTO;
import com.example.shoppinglist.model.entity.User;

public interface UserService {
    boolean passwordsMatch(UserRegisterDTO userRegisterDTO);

    void register(UserRegisterDTO userRegisterDTO);

    boolean checkAvailable(UserRegisterDTO userRegisterDTO);


    boolean login(UserLoginDTO userLoginDTO);

    void logout();

    User getLoggedUser();

    void apply(User user);

    boolean isLogged();
}
