package org.ccccye.user.controller;

import org.ccccye.user.dao.UserRepository;
import org.ccccye.user.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

@RestController
public class UserController {
    private static final Logger Logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository UserRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            Collection<? extends GrantedAuthority> GrantedAuthoritys = userDetails.getAuthorities();
            for (GrantedAuthority g : GrantedAuthoritys){
                UserController.Logger.info("当前用户是{},角色是{}", userDetails.getUsername(), g.getAuthority());
            }
        }else{
            UserController.Logger.info("其他用户");
            // do other things
        }

        Optional<User> User = UserRepository.findById(id);
        return User.get();
    }
}
