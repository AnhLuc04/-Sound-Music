package com.example.demo.controller.user;

import com.example.demo.model.User;
import com.example.demo.model.JwtResponse;
import com.example.demo.service.jwt.JwtService;
import com.example.demo.service.user.IUserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService iUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //Đăng nhập tài khoản
    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtService.generateAccessToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //    cap nhat mat khau
    @PutMapping("/changePassword/{id}")
    public ResponseEntity<User> changePassword(@RequestBody User user, @PathVariable Long id) throws NotFoundException {
        User userOptional = iUserService.findById(id).get();
//        if (!userOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        userOptional.setPassword(passwordEncoder.encode(user.getPassword()));
        iUserService.save(userOptional);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/listUser")
    private ResponseEntity<Iterable<User>> listUser() {
        Iterable<User> listUser = iUserService.findAll();
        return new ResponseEntity<Iterable<User>>(listUser, HttpStatus.OK);
    }
}
