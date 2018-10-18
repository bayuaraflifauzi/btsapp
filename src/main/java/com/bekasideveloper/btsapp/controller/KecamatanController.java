package com.bekasideveloper.btsapp.controller;

import com.bekasideveloper.btsapp.model.Kecamatan;
import com.bekasideveloper.btsapp.service.KecamatanService;
import com.bekasideveloper.btsapp.wrapper.output.CustomerMessage;
import com.bekasideveloper.btsapp.wrapper.input.KecamatanInputWrapper;
import com.bekasideveloper.btsapp.wrapper.output.KecamatanWrapper;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class KecamatanController {
    private static final Logger logger = LoggerFactory.getLogger(KecamatanController.class);

    @Autowired
    private KecamatanService kecamatanService;

    @GetMapping("/kecamatan")
    public ResponseEntity<?> getAllKecamatan(){
        List<KecamatanWrapper> kecamatanWrappers = new ArrayList<>();

        if (kecamatanService == null) logger.error("service is null");
        else logger.info("service is not null");

        List<Kecamatan> kecamatans = kecamatanService.getAllKecamatan();
        for (Kecamatan obj : kecamatans) {
            kecamatanWrappers.add(new KecamatanWrapper(obj.getIdKecamatan(), obj.getNamaJenisBarang()));
        }
        return new ResponseEntity<Object>(kecamatanWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/kecamatan/{idKecamatan}", method = RequestMethod.GET)
    public ResponseEntity<?> getKecamatanById(@PathVariable("idKecamatan") String idKecamatan){
        Kecamatan kecamatan = kecamatanService.getKecamatanById(idKecamatan);

        return new ResponseEntity<Object>(new KecamatanWrapper(kecamatan.getIdKecamatan(), kecamatan.getNamaJenisBarang()), HttpStatus.OK);
    }

    @RequestMapping(value = "/create-kecamatan", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> createKecamatan(@RequestBody KecamatanInputWrapper kecamatanInputWrapper){
        kecamatanService.createKecamatan(kecamatanInputWrapper);
        return new ResponseEntity<Object>(new CustomerMessage("create kecamatan"), HttpStatus.OK);
    }

}
