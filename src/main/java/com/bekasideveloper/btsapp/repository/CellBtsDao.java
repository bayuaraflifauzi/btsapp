package com.bekasideveloper.btsapp.repository;

import com.bekasideveloper.btsapp.model.CellBts;
import com.bekasideveloper.btsapp.model.CellBtsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CellBtsDao extends JpaRepository<CellBts, CellBtsId> {

    @Query("select cellBts from CellBts cellBts " +
            "where cellBts.kecamatan.idKecamatan = ?1")
    List<CellBts> findAllByKecamatan(String idKecamatan);

    @Query("select cbts from CellBts cbts " +
            "left join fetch cbts.kecamatan kecamatan " +
            "where kecamatan.idKecamatan = ?1")
    List<CellBts> findAllByIdKecamatan(String idKecamatan);

}
