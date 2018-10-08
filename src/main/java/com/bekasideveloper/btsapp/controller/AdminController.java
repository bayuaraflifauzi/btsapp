package com.bekasideveloper.btsapp.controller;

import com.bekasideveloper.btsapp.wrapper.input.LoginInputWrapper;
import com.bekasideveloper.btsapp.wrapper.output.CustomMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowCredentials = "false")
public class AdminController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private ResponseEntity<?> login(@RequestBody LoginInputWrapper loginInputWrapper){
        if(loginInputWrapper.getUser().equals("Admin") && loginInputWrapper.getPassword().equals("admin")){
            return new ResponseEntity<Object>(new CustomMessage("dummy token"), HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(new CustomMessage("username atau password salah"), HttpStatus.UNAUTHORIZED);
        }
    }
}
