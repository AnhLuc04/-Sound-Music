package com.example.demo.controller.user;

import com.example.demo.model.JwtResponse;
import com.example.demo.model.Role;
import com.example.demo.model.User;

import com.example.demo.service.jwt.JwtService;
import com.example.demo.service.role.IRoleService;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    //Đăng lí tài khoản
    @PostMapping()
    public ResponseEntity<User> create(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (!bindingResult.hasFieldErrors()) {
            Role role = iRoleService.findRoleByName("ROLE_USER");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            user.setAvatar("https://iupac.org/wp-content/uploads/2018/05/default-avatar.png");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (iUserService.checkUser(user.getUsername())) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                iUserService.save(user);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //  Cập nhập tài khoản
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User user1 = iUserService.findByUsername(user.getUsername());
        user1.setFullName(user.getFullName());
        user1.setAddress(user.getAddress());
        user1.setEmail(user.getEmail());
        user1.setAvatar(user.getAvatar());
        iUserService.save(user1);
        return new ResponseEntity<>(HttpStatus.OK);
    }
// kiểm tra userName đã tồn tại chưa
    @PostMapping("/check")
    public ResponseEntity<Boolean> checkUsername(@RequestBody String username) {
        boolean isValid = iUserService.checkUser(username);
        return new ResponseEntity<>(!isValid, HttpStatus.OK);
    }

}
