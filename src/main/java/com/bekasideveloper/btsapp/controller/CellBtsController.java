package com.bekasideveloper.btsapp.controller;

import com.bekasideveloper.btsapp.service.CellBtsService;
import com.bekasideveloper.btsapp.wrapper.input.CellBtsInputWrapper;
import com.bekasideveloper.btsapp.wrapper.output.CustomerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CellBtsController {

    @Autowired
    private CellBtsService cellBtsService;

    @RequestMapping(value = "/cell-bts", method = RequestMethod.GET)
    private ResponseEntity<?> getAllCellBts(){
        return new ResponseEntity<Object>(cellBtsService.getAllCellBts(), HttpStatus.OK);
    }

    @RequestMapping(value = "/cell-bts/{idKecamatan}", method = RequestMethod.GET)
    private ResponseEntity<?> getKecamatanById(@PathVariable("idKecamatan") String idKecamatan){
        return new ResponseEntity<Object>(cellBtsService.getAllCellBtsByIdKecamatan(idKecamatan), HttpStatus.OK);
    }

    @RequestMapping(value = "/create-cell-bts", method = RequestMethod.POST)
    ResponseEntity<?> createKecamatan(@RequestBody CellBtsInputWrapper cellBtsInputWrapper){
        cellBtsService.createCellBts(cellBtsInputWrapper);
        return new ResponseEntity<Object>(new CustomerMessage("create cell bts"), HttpStatus.OK);
    }

}
