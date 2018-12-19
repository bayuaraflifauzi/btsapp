package com.bekasideveloper.btsapp.repository;

import com.bekasideveloper.btsapp.model.Pengajuan;
import com.bekasideveloper.btsapp.model.PengajuanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PengajuanDao extends JpaRepository<Pengajuan, PengajuanId> {
    List<Pengajuan> findByPengajuanId_UserId(String userId);
    List<Pengajuan> findByPengajuanId_IdPengajuan(Long idAjuan);
}
