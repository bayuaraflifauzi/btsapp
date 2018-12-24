package com.bekasideveloper.btsapp.controller;

import com.bekasideveloper.btsapp.model.CellBts;
import com.bekasideveloper.btsapp.model.CellBtsId;
import com.bekasideveloper.btsapp.model.Kecamatan;
import com.bekasideveloper.btsapp.service.CellBtsService;
import com.bekasideveloper.btsapp.wrapper.input.CellBtsInputWrapper;
import com.bekasideveloper.btsapp.wrapper.output.CellBtsWrapper;
import com.bekasideveloper.btsapp.wrapper.output.CustomMessage;
import com.bekasideveloper.btsapp.wrapper.output.CustomerMessage;
import javafx.scene.control.Cell;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "false")
public class CellBtsController {
    public static final Logger LOGGER = LoggerFactory.getLogger(CellBtsController.class);

    @Autowired
    private CellBtsService cellBtsService;

    @RequestMapping(value = "/cell-bts", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCellBts(){
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
    public ResponseEntity<?> getKecamatanById(@PathVariable("idKecamatan") String idKecamatan){
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
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> createKecamatan(@RequestBody CellBtsInputWrapper cellBtsInputWrapper){
        cellBtsService.createCellBts(cellBtsInputWrapper);
        return new ResponseEntity<Object>(new CustomerMessage("create cell bts"), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-bts/{kodeBts}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> deleteBts(@PathVariable("kodeBts") String kodeBts){
        cellBtsService.deleteCellBts(kodeBts);
        return new ResponseEntity<Object>(new CustomMessage("delete cell bts id = " + kodeBts), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-dokumen-permohonan", method = RequestMethod.GET)
    public ResponseEntity<?> getDokumenPermohonan(){
        LOGGER.info("get dokumen permohonan");
        byte[] file = null;

//        File filePath = new File("/home/pemkab/bts_app/SURAT\\ PERMOHONAN\\ REKOMENDASI\\ TITIK\\ KOORDINAT\\ MENARA\\ TELEKOMUNIKASI.docx");
        File filePath = new File("/home/pemkab/bts_app/SURAT PERMOHONAN REKOMENDASI TITIK KOORDINAT MENARA TELEKOMUNIKASI.docx");

        try {
            file = Files.readAllBytes(filePath.toPath());
        } catch (IOException e){
            LOGGER.error(e.toString());
        }

        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType("aplication/vnd.openxmlformats-officedocument.wordprocessingml.document"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("content-disposition", "attachment;filename=SURAT PERMOHONAN REKOMENDASI TITIK KOORDINAT MENARA TELEKOMUNIKASI.docx");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<Object>(file, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/import-data-cell")
    ResponseEntity<?> importDataCell() {
        LOGGER.info("import data cell");
        InputStream in = getClass().getResourceAsStream("/tb_zona.csv");
        ;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new InputStreamReader(in));
            List<CellBts> cellBtsList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] cell = line.split(cvsSplitBy);
                LOGGER.info("lat: " + cell[0] + "; long: " + cell[1] + " id: "+cell[2] + "; kec: "+cell[2].substring(0,4)+"; radius: "+cell[3].replace("\"",""));

                CellBts cellBts = new CellBts();
                cellBts.setKodeCellBts(cell[2].replace("\"",""));
                cellBts.setRadiusCellBts(Integer.parseInt(cell[3].replace("\"","")));
                CellBtsId cellBtsId = new CellBtsId();
                cellBtsId.setLatCellBts(cell[0].replace("\"",""));
                cellBtsId.setLongCellBts(cell[1].replace("\"",""));
                cellBts.setCellBtsId(cellBtsId);

                Kecamatan kecamatan = new Kecamatan();

                kecamatan.setIdKecamatan(cell[2].substring(0,4).replace("\"",""));
                cellBts.setKecamatan(kecamatan);
                cellBtsList.add(cellBts);
            }

            cellBtsService.createCellBts(cellBtsList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new CustomMessage("file import tidak ditemukan"), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new CustomMessage("gagal mengakses file"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomMessage("Import data selesai"), HttpStatus.OK);
    }
}
