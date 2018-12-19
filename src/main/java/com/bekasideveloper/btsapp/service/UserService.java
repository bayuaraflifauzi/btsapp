package com.bekasideveloper.btsapp.service;

import com.bekasideveloper.btsapp.model.Pengajuan;
import com.bekasideveloper.btsapp.model.Perusahaan;
import com.bekasideveloper.btsapp.model.User;

import java.util.List;

public interface UserService {
    public void createUser(User user, Perusahaan perusahaan);
    public void createAdmin();
    public User getUser(String userName);
    public void createAjuan(Pengajuan pengajuan);
    public Perusahaan getPerusahaan(String email);
    public List<Pengajuan> getAjuanHistory(String idPengaju);
    public Pengajuan getAjuanById(String idAjuan);
    public List<Pengajuan> getAllPengajuan();
    public List<Perusahaan> getAllPerusahaan();
}
