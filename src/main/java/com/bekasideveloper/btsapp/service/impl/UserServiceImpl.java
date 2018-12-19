package com.bekasideveloper.btsapp.service.impl;

import com.bekasideveloper.btsapp.model.Perusahaan;
import com.bekasideveloper.btsapp.model.User;
import com.bekasideveloper.btsapp.repository.PerusahaanDao;
import com.bekasideveloper.btsapp.repository.UserDao;
import com.bekasideveloper.btsapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private PerusahaanDao perusahaanDao;

    @Override
    public void createUser(User user, Perusahaan perusahaan) {
        if (userDao.save(user) == null) {

        }
        if (perusahaanDao.save(perusahaan) == null) {

        }
    }

    @Override
    public void createAdmin() {

    }

    @Override
    public User getUser(String userName) {
        User user = userDao.findByUserId(userName);
        if (user==null) {
            Perusahaan p = perusahaanDao.getByEmail(userName);
            user = userDao.getOne(p.getUserId());
        }

        return user;
    }
}
