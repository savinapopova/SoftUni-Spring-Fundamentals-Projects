package com.example.andreys.model.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class UserRegisterDTO {

    @NotBlank
    @Size(min = 3)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3)
    private String password;

    @NotBlank
    @Size(min = 3)
    private String confirmPassword;

    @NotNull
    @Positive
    private BigDecimal budget;

    public UserRegisterDTO(String username, String email, String password,
                           String confirmPassword, BigDecimal budget) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.budget = budget;
    }

    public UserRegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public UserRegisterDTO setBudget(BigDecimal budget) {
        this.budget = budget;
        return this;
    }
}
