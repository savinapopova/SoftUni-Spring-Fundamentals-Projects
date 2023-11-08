package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.CreateUserDTO;
import com.example.spotifyplaylistapp.model.dto.UserLoginDTO;
import com.example.spotifyplaylistapp.model.entity.User;

public interface UserService {
    boolean checkAvailable(CreateUserDTO createUserDTO);

    boolean passwordsMatch(String password, String confirmPassword);

    void register(CreateUserDTO createUserDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();

    User getLoggedUser();

    void clearPlaylist();

    boolean isLogged();
}
