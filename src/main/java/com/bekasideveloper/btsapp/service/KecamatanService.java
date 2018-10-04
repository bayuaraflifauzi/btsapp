package com.bekasideveloper.btsapp.service;

import com.bekasideveloper.btsapp.model.Kecamatan;
import com.bekasideveloper.btsapp.wrapper.input.KecamatanInputWrapper;

import java.util.List;

public interface KecamatanService {

    List<Kecamatan> getAllKecamatan();
    Kecamatan getKecamatanById(String idKecamatan);
    void createKecamatan(KecamatanInputWrapper kecamatanInputWrapper);
}
