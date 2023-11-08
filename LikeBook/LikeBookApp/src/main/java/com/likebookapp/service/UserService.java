package com.likebookapp.service;

import com.likebookapp.model.dto.UserLoginDTO;
import com.likebookapp.model.dto.UserRegisterDTO;
import com.likebookapp.model.entity.User;

public interface UserService {
    boolean checkAvailable(UserRegisterDTO userRegisterDTO);

    boolean exists(UserLoginDTO userLoginDTO);

    boolean passwordsMatch(UserRegisterDTO userRegisterDTO);

    void register(UserRegisterDTO userRegisterDTO);

    User getLoggedUser();


    void logout();

    boolean loggedUser();
}
