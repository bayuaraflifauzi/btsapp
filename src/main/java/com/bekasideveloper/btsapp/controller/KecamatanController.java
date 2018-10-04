package com.bekasideveloper.btsapp.controller;

import com.bekasideveloper.btsapp.model.Kecamatan;
import com.bekasideveloper.btsapp.service.KecamatanService;
import com.bekasideveloper.btsapp.wrapper.output.CustomerMessage;
import com.bekasideveloper.btsapp.wrapper.input.KecamatanInputWrapper;
import com.bekasideveloper.btsapp.wrapper.output.KecamatanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "false")
public class KecamatanController {

    @Autowired
    private KecamatanService kecamatanService;

    @RequestMapping(value = "/kecamatan", method = RequestMethod.GET)
    private ResponseEntity<?> getAllKecamatan(){
        List<KecamatanWrapper> kecamatanWrappers = new ArrayList<>();
        List<Kecamatan> kecamatans = kecamatanService.getAllKecamatan();
        for (Kecamatan obj : kecamatans) {
            kecamatanWrappers.add(new KecamatanWrapper(obj.getIdKecamatan(), obj.getNamaJenisBarang()));
        }
        return new ResponseEntity<Object>(kecamatanWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/kecamatan/{idKecamatan}", method = RequestMethod.GET)
    private ResponseEntity<?> getKecamatanById(@PathVariable("idKecamatan") String idKecamatan){
        Kecamatan kecamatan = kecamatanService.getKecamatanById(idKecamatan);

        return new ResponseEntity<Object>(new KecamatanWrapper(kecamatan.getIdKecamatan(), kecamatan.getNamaJenisBarang()), HttpStatus.OK);
    }

    @RequestMapping(value = "/create-kecamatan", method = RequestMethod.POST)
    ResponseEntity<?> createKecamatan(@RequestBody KecamatanInputWrapper kecamatanInputWrapper){
        kecamatanService.createKecamatan(kecamatanInputWrapper);
        return new ResponseEntity<Object>(new CustomerMessage("create kecamatan"), HttpStatus.OK);
    }

}
