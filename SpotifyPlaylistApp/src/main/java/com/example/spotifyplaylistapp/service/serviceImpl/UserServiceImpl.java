package com.example.spotifyplaylistapp.service.serviceImpl;

import com.example.spotifyplaylistapp.model.dto.CreateUserDTO;
import com.example.spotifyplaylistapp.model.dto.UserLoginDTO;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private LoggedUser userSession;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }

    @Override
    public boolean checkAvailable(CreateUserDTO createUserDTO) {
        Optional<User> optionalUser = this.userRepository
                .findByUsernameOrEmail(createUserDTO.getUsername(), createUserDTO.getEmail());
        if (optionalUser.isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean passwordsMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    @Override
    public void register(CreateUserDTO createUserDTO) {
        User user = new User(createUserDTO.getUsername(),
                             passwordEncoder.encode(createUserDTO.getPassword()),
                             createUserDTO.getEmail());
        this.userRepository.save(user);
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> optionalUser = this.userRepository.findByUsername(userLoginDTO.getUsername());
        if (optionalUser.isEmpty()) {
            return false;
        }
        User user = optionalUser.get();
        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            return false;
        }
        this.userSession.login(user);
        return true;
    }

    @Override
    public void logout() {
        this.userSession.logout();
    }

    @Override
    public User getLoggedUser() {
        Optional<User> user = this.userRepository.findById(this.userSession.getId());
        return user.get();
    }

    @Override
    public void clearPlaylist() {
        User user = this.getLoggedUser();
        user.getPlaylist().clear();
        this.userRepository.save(user);
    }

    @Override
    public boolean isLogged() {
        return this.userSession.getId() > 0;
    }
}
