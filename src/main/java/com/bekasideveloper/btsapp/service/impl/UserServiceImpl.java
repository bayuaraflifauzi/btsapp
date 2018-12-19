package com.bekasideveloper.btsapp.service.impl;

import com.bekasideveloper.btsapp.model.Perusahaan;
import com.bekasideveloper.btsapp.model.User;
import com.bekasideveloper.btsapp.repository.PerusahaanDao;
import com.bekasideveloper.btsapp.repository.UserDao;
import com.bekasideveloper.btsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {
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
}
