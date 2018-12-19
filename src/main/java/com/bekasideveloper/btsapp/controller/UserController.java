package com.bekasideveloper.btsapp.controller;

import com.bekasideveloper.btsapp.model.Perusahaan;
import com.bekasideveloper.btsapp.model.User;
import com.bekasideveloper.btsapp.service.UserService;
import com.bekasideveloper.btsapp.wrapper.input.UserRegistrationWrapper;
import com.bekasideveloper.btsapp.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/register-user")
    public ResponseEntity<?> getAllKecamatan(@RequestBody UserRegistrationWrapper wrapper){
        logger.info("register user");

        String userId = String.valueOf(new Date().getTime());
        User user = new User();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(wrapper.getPassword()));
        user.setRole(1);

        Perusahaan perusahaan = new Perusahaan();
        perusahaan.setUserId(userId);
        perusahaan.setEmail(wrapper.getEmail());
        perusahaan.setNpwpd(wrapper.getNpwpd());
        perusahaan.setNamaPerusahaan(wrapper.getNamaPerusahaan());
        perusahaan.setNamaPendaftar(wrapper.getNamaPendaftar());
        perusahaan.setAlamatPerusahaan(wrapper.getAlamatPerusahaan());
        perusahaan.setNoTelp(wrapper.getNoTelp());

        userService.createUser(user, perusahaan);

        return new ResponseEntity<>(new CustomMessage("pendaftaran berhasil"), HttpStatus.OK);
    }
}
