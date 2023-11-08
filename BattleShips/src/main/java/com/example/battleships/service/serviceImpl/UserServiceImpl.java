package com.example.battleships.service.serviceImpl;

import com.example.battleships.LoggedUser;
import com.example.battleships.model.dto.UserLoginDTO;
import com.example.battleships.model.dto.UserRegistrationDTO;
import com.example.battleships.model.entity.User;
import com.example.battleships.repository.UserRepository;
import com.example.battleships.service.UserService;
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
    public void register(UserRegistrationDTO userRegistrationDTO) {

        if (!userRegistrationDTO.getPassword()
                .equals(userRegistrationDTO.getConfirmPassword())) {
            throw new RuntimeException("password.match");
        }
        Optional<User> byUsername = userRepository.findByUsername(userRegistrationDTO.getUsername());

        if (byUsername.isPresent()) {
            throw new RuntimeException("username.used");
        }

        Optional<User> byEmail = userRepository.findByEmail(userRegistrationDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        User user = new User(userRegistrationDTO.getUsername(),
                userRegistrationDTO.getFullName(),
                passwordEncoder.encode(userRegistrationDTO.getPassword()),
                userRegistrationDTO.getEmail());

        userRepository.save(user);
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {

        if (!exists(userLoginDTO)) {
            return false;
        }

        return true;
    }

    @Override
    public void logout() {
        userSession.logout();
    }

    @Override
    public boolean isLoggedIn() {
        return this.userSession.getId() > 0;
    }

    @Override
    public long getLoggedId() {
        return this.userSession.getId();
    }


    private boolean exists(UserLoginDTO userLoginDTO) {
       Optional<User> optionalUser = userRepository
               .findByUsername(userLoginDTO.getUsername());
       if (optionalUser.isEmpty()) {
           return false;
       }
       User user = optionalUser.get();
       String encryptedPassword = user.getPassword();

       if (!passwordEncoder.matches(userLoginDTO.getPassword(), encryptedPassword)) {
           return false;
       }
       userSession.login(optionalUser.get());
       return true;
    }
}
