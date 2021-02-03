package com.example.demo.service.user;

import com.example.demo.model.User;
import com.example.demo.repository.IUserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Iterable<User> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) throws NotFoundException {
        return iUserRepository.findById(id);
    }

    @Override
    public User save(User model) {
       return iUserRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        iUserRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }

    @Override
    public boolean checkUser(String username) {
        return iUserRepository.findByUsername(username) != null;
    }

    @Override
    public boolean checkPassword(String username, String password) {
        String userPassword = iUserRepository.findByUsername(username).getPassword();
        CharSequence passwordEncode = password;
        if (passwordEncoder.matches(passwordEncode, userPassword)) {
            return true;
        } else {
            return false;
        }
    }
}
