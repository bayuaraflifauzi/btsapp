package com.bekasideveloper.btsapp.repository;

import com.bekasideveloper.btsapp.model.Kecamatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KecamatanDao extends JpaRepository<Kecamatan, String> {
}
