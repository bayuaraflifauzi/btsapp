package com.bekasideveloper.btsapp.controller;

import com.bekasideveloper.btsapp.model.CellBts;
import com.bekasideveloper.btsapp.service.CellBtsService;
import com.bekasideveloper.btsapp.wrapper.input.CellBtsInputWrapper;
import com.bekasideveloper.btsapp.wrapper.output.CellBtsWrapper;
import com.bekasideveloper.btsapp.wrapper.output.CustomerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "false")
public class CellBtsController {

    @Autowired
    private CellBtsService cellBtsService;

    @RequestMapping(value = "/cell-bts", method = RequestMethod.GET)
    private ResponseEntity<?> getAllCellBts(){
        List<CellBtsWrapper> cellBtsWrappers = new ArrayList<>();
        List<CellBts> cellBtses = cellBtsService.getAllCellBts();
        for (CellBts obj : cellBtses) {
            cellBtsWrappers.add(new CellBtsWrapper(obj.getKodeCellBts(),
                    obj.getRadiusCellBts(),
                    obj.getCellBtsId().getLatCellBts(),
                    obj.getCellBtsId().getLongCellBts()));
        }
        return new ResponseEntity<Object>(cellBtsWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/cell-bts/{idKecamatan}", method = RequestMethod.GET)
    private ResponseEntity<?> getKecamatanById(@PathVariable("idKecamatan") String idKecamatan){
        List<CellBtsWrapper> cellBtsWrappers = new ArrayList<>();
        List<CellBts> cellBtses = cellBtsService.getAllCellBtsByIdKecamatan(idKecamatan);
        for (CellBts obj : cellBtses) {
            cellBtsWrappers.add(new CellBtsWrapper(obj.getKodeCellBts(),
                    obj.getRadiusCellBts(),
                    obj.getCellBtsId().getLatCellBts(),
                    obj.getCellBtsId().getLongCellBts()));
        }
        return new ResponseEntity<Object>(cellBtsWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/create-cell-bts", method = RequestMethod.POST)
    ResponseEntity<?> createKecamatan(@RequestBody CellBtsInputWrapper cellBtsInputWrapper){
        cellBtsService.createCellBts(cellBtsInputWrapper);
        return new ResponseEntity<Object>(new CustomerMessage("create cell bts"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-dokumen-permohonan", method = RequestMethod.GET)
    private ResponseEntity<?> getDokumenPermohonan(){
        byte[] file = null;

        File filePath = new File("/home/pemkab/bts_app/SURAT\\ PERMOHONAN\\ REKOMENDASI\\ TITIK\\ KOORDINAT\\ MENARA\\ TELEKOMUNIKASI.docx");

        try {
            file = Files.readAllBytes(filePath.toPath());
        } catch (IOException e){
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("aplication/vnd.openxmlformats-officedocument.wordprocessingml.document"));
        headers.add("content-disposition", "inline;filename=SURAT PERMOHONAN REKOMENDASI TITIK KOORDINAT MENARA TELEKOMUNIKASI");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }
}
