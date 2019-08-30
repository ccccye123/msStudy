package org.ccccye.user.controller;

import org.ccccye.user.dao.UserRepository;
import org.ccccye.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository UserRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        Optional<User> User = UserRepository.findById(id);

        return User.get();
    }
}
