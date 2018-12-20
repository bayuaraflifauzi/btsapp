package com.bekasideveloper.btsapp.service.impl;

import com.bekasideveloper.btsapp.model.Pengajuan;
import com.bekasideveloper.btsapp.model.Perusahaan;
import com.bekasideveloper.btsapp.model.User;
import com.bekasideveloper.btsapp.repository.PengajuanDao;
import com.bekasideveloper.btsapp.repository.PerusahaanDao;
import com.bekasideveloper.btsapp.repository.UserDao;
import com.bekasideveloper.btsapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private PerusahaanDao perusahaanDao;
    @Autowired
    private PengajuanDao pengajuanDao;

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
            if (p!=null)
                user = userDao.getOne(p.getUserId());
        }

        return user;
    }

    @Override
    public void createAjuan(Pengajuan pengajuan) {
        pengajuanDao.save(pengajuan);
    }

    @Override
    public Perusahaan getPerusahaan(String email) {
        return perusahaanDao.getByEmail(email);
    }

    @Override
    public List<Pengajuan> getAjuanHistory(String idPengaju) {
        return pengajuanDao.findByPengajuanId_UserId(idPengaju);
    }

    @Override
    public Pengajuan getAjuanById(String idAjuan) {
        List<Pengajuan> pList = pengajuanDao.findByPengajuanId_IdPengajuan(Long.valueOf(idAjuan));
        if (!pList.isEmpty())
            return pList.get(0);
        return null;
    }

    @Override
    public List<Pengajuan> getAllPengajuan() {
        return pengajuanDao.findAll();
    }

    @Override
    public List<Perusahaan> getAllPerusahaan() {
        return perusahaanDao.findAll();
    }
}
