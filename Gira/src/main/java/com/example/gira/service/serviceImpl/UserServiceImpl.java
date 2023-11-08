package com.example.gira.service.serviceImpl;

import com.example.gira.model.dto.UserLoginDTO;
import com.example.gira.model.dto.UserRegisterDTO;
import com.example.gira.model.entity.User;
import com.example.gira.model.session.LoggedUser;
import com.example.gira.repository.UserRepository;
import com.example.gira.service.UserService;
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
    public boolean passwordsMatch(UserRegisterDTO userRegisterDTO) {
        return userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword());
    }

    @Override
    public boolean checkAvailable(UserRegisterDTO userRegisterDTO) {
        Optional<User> optionalUser = this.userRepository
                .findByUsernameOrEmail(userRegisterDTO.getUsername(), userRegisterDTO.getEmail());
        return optionalUser.isEmpty();
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        User user = new User(userRegisterDTO.getUsername(),
                passwordEncoder.encode(userRegisterDTO.getPassword()),
                userRegisterDTO.getEmail());
        this.userRepository.save(user);
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> optionalUser = this.userRepository
                .findByEmail(userLoginDTO.getEmail());
        if (optionalUser.isEmpty()) {
            return false;
        }
        User user = optionalUser.get();
        String rawPassword = userLoginDTO.getPassword();
        String encryptedPassword = user.getPassword();
        if (!passwordEncoder.matches(rawPassword, encryptedPassword)) {
            return false;
        }
        this.userSession.login(user);

        return true;
    }

    @Override
    public boolean isLogged() {

        return this.userSession.isLogged();
    }

    @Override
    public void logout() {
        this.userSession.logout();
    }
}
