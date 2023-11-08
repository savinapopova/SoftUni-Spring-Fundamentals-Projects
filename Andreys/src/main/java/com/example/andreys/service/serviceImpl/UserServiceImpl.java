package com.example.andreys.service.serviceImpl;

import com.example.andreys.model.dto.UserLoginDTO;
import com.example.andreys.model.dto.UserRegisterDTO;
import com.example.andreys.model.entity.User;
import com.example.andreys.repository.UserRepository;
import com.example.andreys.service.UserService;
import com.example.andreys.session.LoggedUser;
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
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           LoggedUser userSession) {
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
                userRegisterDTO.getEmail(),
                userRegisterDTO.getBudget());
        this.userRepository.save(user);
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {

       Optional<User> optionalUser = this.userRepository
               .findByUsername(userLoginDTO.getUsername());

       if (optionalUser.isEmpty()) {
           return false;
       }

       User user = optionalUser.get();

       String encryptedPassword = user.getPassword();
       String rawPassword = userLoginDTO.getPassword();

       if (!passwordEncoder.matches(rawPassword, encryptedPassword)) {
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
    public boolean isLogged() {

        return this.userSession.isLogged();
    }
}
