package com.example.coffeeshop.service.serviceImpl;

import com.example.coffeeshop.model.dto.UserDTO;
import com.example.coffeeshop.model.dto.UserLoginDTO;
import com.example.coffeeshop.model.dto.UserRegisterDTO;
import com.example.coffeeshop.model.entity.User;
import com.example.coffeeshop.repository.UserRepository;
import com.example.coffeeshop.service.UserService;
import com.example.coffeeshop.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .findByUsernameOrEmail(userRegisterDTO.getUsername(),
                userRegisterDTO.getEmail());

        return optionalUser.isEmpty();
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        User user = new User(userRegisterDTO.getUsername(),
                userRegisterDTO.getFirstName(),
                userRegisterDTO.getLastName(),
                passwordEncoder.encode(userRegisterDTO.getPassword()),
                userRegisterDTO.getEmail());
        this.userRepository.save(user);

    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
       Optional<User> optional = this.userRepository.findByUsername(userLoginDTO.getUsername());
       if (optional.isEmpty()) {
           return false;
       }
       User user = optional.get();
       String rawPassword = userLoginDTO.getPassword();

       if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
           return false;
       }

       this.userSession.login(user);

       return true;
    }

    @Override
    public User getLoggedUser() {
        Optional<User> optionalUser = this.userRepository.findById(this.userSession.getId());
        return optionalUser.get();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDTO> userDTOList = users.stream().map(UserDTO::new)
                .collect(Collectors.toList());

        return userDTOList;
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
