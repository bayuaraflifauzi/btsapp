package com.bekasideveloper.btsapp.service.impl;

import com.bekasideveloper.btsapp.model.Kecamatan;
import com.bekasideveloper.btsapp.repository.KecamatanDao;
import com.bekasideveloper.btsapp.service.KecamatanService;
import com.bekasideveloper.btsapp.wrapper.input.KecamatanInputWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("KecamatanService")
public class KecamatanServiceImpl implements KecamatanService {

    @Autowired
    private KecamatanDao kecamatanDao;

    @Override
    public List<Kecamatan> getAllKecamatan() {
        return kecamatanDao.findAll();
    }

    @Override
    public Kecamatan getKecamatanById(String idKecamatan) {
        return kecamatanDao.getOne(idKecamatan);
    }

    @Override
    public void createKecamatan(KecamatanInputWrapper kecamatanInputWrapper) {
        Kecamatan kecamatan = new Kecamatan();

        kecamatan.setIdKecamatan(kecamatanInputWrapper.getIdKecamatan());
        kecamatan.setNamaJenisBarang(kecamatanInputWrapper.getNamaKecamatan());

        kecamatanDao.save(kecamatan);
    }
}
