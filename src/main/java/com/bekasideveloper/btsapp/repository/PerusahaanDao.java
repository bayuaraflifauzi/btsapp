package com.bekasideveloper.btsapp.repository;

import com.bekasideveloper.btsapp.model.Perusahaan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerusahaanDao extends JpaRepository<Perusahaan, String> {
    Perusahaan getByEmail(String email);
}
