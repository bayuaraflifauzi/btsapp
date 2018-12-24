package com.bekasideveloper.btsapp.controller;

import com.bekasideveloper.btsapp.model.Pengajuan;
import com.bekasideveloper.btsapp.model.Perusahaan;
import com.bekasideveloper.btsapp.model.User;
import com.bekasideveloper.btsapp.repository.UserDao;
import com.bekasideveloper.btsapp.security.JWTTokenProvider;
import com.bekasideveloper.btsapp.service.UserService;
import com.bekasideveloper.btsapp.service.impl.UserDetailsServiceImpl;
import com.bekasideveloper.btsapp.wrapper.input.LoginInputWrapper;
import com.bekasideveloper.btsapp.wrapper.input.UpdateStatusAjuanWrapper;
import com.bekasideveloper.btsapp.wrapper.output.AjuanWrapper;
import com.bekasideveloper.btsapp.wrapper.output.CustomMessage;
import com.bekasideveloper.btsapp.wrapper.output.JWTAuthenticationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowCredentials = "false")
public class AdminController {
    @Autowired private UserDao userDao;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JWTTokenProvider tokenProvider;
    @Autowired private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

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
        User user = userService.getUser(loginInputWrapper.getUser());
        Authentication authentication
                = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserId(), loginInputWrapper.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = String.valueOf(user.getRole())+" "+tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthenticationResponse(jwt));
    }

    @PostMapping("/daftar-admin")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(0);
        userDao.save(user);

        return new ResponseEntity<>("success register user", HttpStatus.CREATED);
    }

    @GetMapping("/get-user")
    public ResponseEntity<?> getUser() {
        List<User> users = userDao.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get-all-ajuan")
    public ResponseEntity<?> getAllAjuan() {
        logger.info("get all ajuan");

        List<AjuanWrapper> wrappers = new ArrayList<>();
        List<Pengajuan> ajuanList = userService.getAllPengajuan();
        Map<String, Perusahaan> perusahaanMap = convertPerusahaanListToMap(userService.getAllPerusahaan());

        for (Pengajuan pengajuan : ajuanList) {
            if (pengajuan.getStatusAktif()==1) {
                Perusahaan p = perusahaanMap.get(pengajuan.getPengajuanId().getUserId());
                wrappers.add(new AjuanWrapper(
                        p.getEmail(),
                        p.getNpwpd(),
                        p.getNamaPerusahaan(),
                        p.getNamaPendaftar(),
                        p.getAlamatPerusahaan(),
                        p.getNoTelp(),
                        String.valueOf(pengajuan.getPengajuanId().getIdPengajuan()),
                        pengajuan.getIdKecamatan(),
                        pengajuan.getKecamatan(),
                        pengajuan.getLongitudeAjuan(),
                        pengajuan.getLatitudeAjuan(),
                        pengajuan.getPengajuanId().getIdPengajuan(),
                        pengajuan.getStatus()
                ));
            }
        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    @PostMapping("/update-status-ajuan")
    public ResponseEntity<?> updateStatusAjuan(@RequestBody UpdateStatusAjuanWrapper wrapper) {
        logger.info("update status ajuan");

        Pengajuan p = userService.getAjuanById(wrapper.getAjuanId());
        if (p==null)
            return new ResponseEntity<>(new CustomMessage("data tidak ditemukan"), HttpStatus.INTERNAL_SERVER_ERROR);
        p.setStatus(wrapper.getStatusBaru());

        userService.createAjuan(p);

        return new ResponseEntity<>(new CustomMessage("status ajuan berhasil diubah"), HttpStatus.OK);
    }

    private Map<String, Perusahaan> convertPerusahaanListToMap(List<Perusahaan> perusahaanList) {
        Map<String, Perusahaan> mapper = new HashMap<>();
        for (Perusahaan p : perusahaanList) {
            mapper.put(p.getUserId(),p);
        }

        return mapper;
    }
}
