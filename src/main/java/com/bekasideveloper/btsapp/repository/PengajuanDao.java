package com.bekasideveloper.btsapp.repository;

import com.bekasideveloper.btsapp.model.Pengajuan;
import com.bekasideveloper.btsapp.model.PengajuanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PengajuanDao extends JpaRepository<Pengajuan, PengajuanId> {

}
