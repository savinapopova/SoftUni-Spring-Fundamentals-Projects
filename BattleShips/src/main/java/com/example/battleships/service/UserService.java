package com.example.battleships.service;

import com.example.battleships.model.dto.UserLoginDTO;
import com.example.battleships.model.dto.UserRegistrationDTO;

public interface UserService {
   public void register(UserRegistrationDTO userRegistrationDTO);

    public boolean login(UserLoginDTO userLoginDTO);

    public void logout();

    boolean isLoggedIn();

    long getLoggedId();
}
