package com.example.demo.service.user;

import com.example.demo.model.User;
import com.example.demo.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String username);
    boolean checkUser(String username);
    boolean checkPassword(String username, String password);
}
