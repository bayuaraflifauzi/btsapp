package com.bekasideveloper.btsapp.controller;

import com.bekasideveloper.btsapp.service.KecamatanService;
import com.bekasideveloper.btsapp.wrapper.output.CustomerMessage;
import com.bekasideveloper.btsapp.wrapper.input.KecamatanInputWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KecamatanController {

    @Autowired
    private KecamatanService kecamatanService;

    @RequestMapping(value = "/kecamatan", method = RequestMethod.GET)
    private ResponseEntity<?> getAllKecamatan(){
        return new ResponseEntity<Object>(kecamatanService.getAllKecamatan(), HttpStatus.OK);
    }

    @RequestMapping(value = "/kecamatan/{idKecamatan}", method = RequestMethod.GET)
    private ResponseEntity<?> getKecamatanById(@PathVariable("idKecamatan") String idKecamatan){
        return new ResponseEntity<Object>(kecamatanService.getKecamatanById(idKecamatan), HttpStatus.OK);
    }

    @RequestMapping(value = "/create-kecamatan", method = RequestMethod.POST)
    ResponseEntity<?> createKecamatan(@RequestBody KecamatanInputWrapper kecamatanInputWrapper){
        kecamatanService.createKecamatan(kecamatanInputWrapper);
        return new ResponseEntity<Object>(new CustomerMessage("create kecamatan"), HttpStatus.OK);
    }

}
