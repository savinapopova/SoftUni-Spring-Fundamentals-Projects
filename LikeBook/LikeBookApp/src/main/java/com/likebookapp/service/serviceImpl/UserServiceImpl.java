package com.likebookapp.service.serviceImpl;

import com.likebookapp.model.dto.UserLoginDTO;
import com.likebookapp.model.dto.UserRegisterDTO;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.service.UserService;
import com.likebookapp.session.LoggedUser;
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
    public boolean checkAvailable(UserRegisterDTO userRegisterDTO) {

        Optional<User> optionalUser = userRepository
                .findByUsernameOrEmail(userRegisterDTO.getUsername(), userRegisterDTO.getEmail());
        return optionalUser.isEmpty();
    }

    @Override
    public boolean exists(UserLoginDTO userLoginDTO) {
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
        userSession.login(user);
        return true;
    }

    @Override
    public boolean passwordsMatch(UserRegisterDTO userRegisterDTO) {

        return userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword());
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        User user = new User(userRegisterDTO.getUsername(),
                passwordEncoder.encode(userRegisterDTO.getPassword()),
                userRegisterDTO.getEmail());
        this.userRepository.save(user);
    }

    @Override
    public User getLoggedUser() {
        Optional<User> optionalUser = userRepository.findById(userSession.getId());
        return optionalUser.get();
    }

    @Override
    public void logout() {
        this.userSession.logout();
    }

    @Override
    public boolean loggedUser() {
        return this.userSession.getId() > 0;
    }


}
