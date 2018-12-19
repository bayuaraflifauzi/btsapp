package com.bekasideveloper.btsapp.service.impl;

import com.bekasideveloper.btsapp.model.User;
import com.bekasideveloper.btsapp.repository.UserDao;
import com.bekasideveloper.btsapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Primary
@Service("UserDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUser(s);

        List<GrantedAuthority> list = new ArrayList<>();

        if (user == null) {
            logger.error("user not found in db");
            throw new UsernameNotFoundException(s);
        }

        String ROLE_PREFIX = "ROLE_";
        String role = "";

        switch (user.getRole()) {
            case 0 :
                role = ROLE_PREFIX + "ADMIN";
                break;
            case 1 :
                role = ROLE_PREFIX + "USER";
                break;
        }

        list.add(new SimpleGrantedAuthority(role));

        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), list);
    }
}
