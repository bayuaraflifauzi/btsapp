package com.bekasideveloper.btsapp.service.impl;

import com.bekasideveloper.btsapp.model.CellBts;
import com.bekasideveloper.btsapp.model.CellBtsId;
import com.bekasideveloper.btsapp.model.Kecamatan;
import com.bekasideveloper.btsapp.repository.CellBtsDao;
import com.bekasideveloper.btsapp.service.CellBtsService;
import com.bekasideveloper.btsapp.wrapper.input.CellBtsInputWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("CellBtsService")
@Transactional
public class CellBtsServiceImpl implements CellBtsService {

    @Autowired
    private CellBtsDao cellBtsDao;

    @Override
    public List<CellBts> getAllCellBts() {
        return cellBtsDao.findAll();
    }

    @Override
    public List<CellBts> getAllCellBtsByIdKecamatan(String idKecamatan) {
        return cellBtsDao.findAllByKecamatan(idKecamatan);
    }

    @Override
    public void createCellBts(CellBtsInputWrapper cellBtsInputWrapper) {
        CellBts cellBts = new CellBts();

        cellBts.setKodeCellBts(cellBtsInputWrapper.getKodeCellBts());
        cellBts.setRadiusCellBts(cellBtsInputWrapper.getRadiusCellBts());

        CellBtsId cellBtsId = new CellBtsId();

        cellBtsId.setLatCellBts(cellBtsInputWrapper.getLatCellBts());
        cellBtsId.setLongCellBts(cellBtsInputWrapper.getLongCellBts());
        cellBts.setCellBtsId(cellBtsId);

        Kecamatan kecamatan = new Kecamatan();

        kecamatan.setIdKecamatan(cellBtsInputWrapper.getIdKecamatan());
        cellBts.setKecamatan(kecamatan);

        cellBtsDao.save(cellBts);
    }

    @Override
    public void createCellBts(List<CellBts> cellBtsList) {
        for (CellBts cellBts : cellBtsList)
            cellBtsDao.save(cellBts);
    }

    @Override
    public void deleteCellBts(String kodeCellBts){
        cellBtsDao.deleteCellBtsByKodeCellBts(kodeCellBts);
    }
}
