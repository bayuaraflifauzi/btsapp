package com.bekasideveloper.btsapp.controller;

import com.bekasideveloper.btsapp.model.User;
import com.bekasideveloper.btsapp.repository.UserDao;
import com.bekasideveloper.btsapp.security.JWTTokenProvider;
import com.bekasideveloper.btsapp.wrapper.input.LoginInputWrapper;
import com.bekasideveloper.btsapp.wrapper.output.CustomMessage;
import com.bekasideveloper.btsapp.wrapper.output.JWTAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "false")
public class AdminController {
    @Autowired private UserDao userDao;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JWTTokenProvider tokenProvider;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginInputWrapper loginInputWrapper){
        if(loginInputWrapper.getUser().equals("Admin") && loginInputWrapper.getPassword().equals("admin")){
            return new ResponseEntity<Object>(new CustomMessage("dummy token"), HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(new CustomMessage("username atau password salah"), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/login-secure", method = RequestMethod.POST)
    public ResponseEntity<?> loginSecure(@RequestBody LoginInputWrapper loginInputWrapper){
        Authentication authentication
                = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginInputWrapper.getUser(), loginInputWrapper.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthenticationResponse(jwt));
    }

    @PostMapping("/daftar-admin")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);

        return new ResponseEntity<>("success register user", HttpStatus.CREATED);
    }

    @GetMapping("/get-user")
    public ResponseEntity<?> getUser() {
        List<User> users = userDao.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
