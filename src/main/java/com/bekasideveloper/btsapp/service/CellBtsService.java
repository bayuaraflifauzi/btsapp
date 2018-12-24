package com.bekasideveloper.btsapp.service;

import com.bekasideveloper.btsapp.model.CellBts;
import com.bekasideveloper.btsapp.wrapper.input.CellBtsInputWrapper;

import java.util.List;

public interface CellBtsService {

    List<CellBts> getAllCellBts();
    List<CellBts> getAllCellBtsByIdKecamatan(String idKecamatan);

    void createCellBts(CellBtsInputWrapper cellBtsInputWrapper);
    void createCellBts(List<CellBts> cellBtsList);
    void deleteCellBts(String kodeBts);
}
