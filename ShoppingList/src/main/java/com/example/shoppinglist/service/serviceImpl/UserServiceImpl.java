package com.example.shoppinglist.service.serviceImpl;

import com.example.shoppinglist.model.dto.UserLoginDTO;
import com.example.shoppinglist.model.dto.UserRegisterDTO;
import com.example.shoppinglist.model.entity.User;
import com.example.shoppinglist.repository.UserRepository;
import com.example.shoppinglist.service.UserService;
import com.example.shoppinglist.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
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
    public void register(UserRegisterDTO userRegisterDTO) {
        User user = new User(userRegisterDTO.getUsername(),
                passwordEncoder.encode(userRegisterDTO.getPassword()),
                userRegisterDTO.getEmail());
        this.userRepository.save(user);
    }

    @Override
    public boolean checkAvailable(UserRegisterDTO userRegisterDTO) {
        Optional<User> optionalUser = this.userRepository.findByUsernameOrEmail(userRegisterDTO.getUsername(),
                userRegisterDTO.getEmail());
        return optionalUser.isEmpty();
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {

        Optional<User> optionalUser = this.userRepository.findByUsername(userLoginDTO.getUsername());

        if (optionalUser.isEmpty()) {
            return false;
        }

        User user = optionalUser.get();

        if(!this.passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
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
    public void apply(User user) {
        this.userRepository.save(user);
    }

    @Override
    public boolean isLogged() {
        return this.userSession.isLogged();
    }

}
