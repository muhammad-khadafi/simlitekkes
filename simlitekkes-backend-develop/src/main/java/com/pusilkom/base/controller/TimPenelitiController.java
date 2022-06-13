/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.controller;

import com.pusilkom.base.dto.PenelitiDTO;
import com.pusilkom.base.dto.PenelitianDTO;
import com.pusilkom.base.dto.TimPenelitiDTO;
import com.pusilkom.base.model.TimPeneliti;
import com.pusilkom.base.service.TimPenelitiService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Helmi Fakhriandi
 */
@RestController
public class TimPenelitiController {

    @Autowired
    TimPenelitiService timPenelitiService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/timPeneliti/getKetuaTimPeneliti/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getKetuaTimPeneliti(@PathVariable Integer idPenelitian){
        ResponseEntity responseEntity = null;
        try {
            TimPeneliti response = timPenelitiService.getKetuaTimPeneliti(idPenelitian);
            responseEntity = ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/timPeneliti/getDataKetua/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getDataKetuaFromIdPenelitian(@PathVariable Integer idPenelitian){
        ResponseEntity responseEntity = null;
        try {
            TimPeneliti response = timPenelitiService.getDataKetuaFromIdPenelitian(idPenelitian);
            responseEntity = ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/timPeneliti/getDataAnggota/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getDataAnggotaFromIdPenelitian(@PathVariable Integer idPenelitian){
        ResponseEntity responseEntity = null;
        try {
            List<PenelitiDTO> response = timPenelitiService.getListPenelitiFromIdPenelitian(idPenelitian, true);
            responseEntity = ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/timPeneliti/getDataAnggotaNP/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getDataAnggotaNPFromIdPenelitian(@PathVariable Integer idPenelitian){
        ResponseEntity responseEntity = null;
        try {
            List<PenelitiDTO> response = timPenelitiService.getListPenelitiFromIdPenelitian(idPenelitian, false);
            responseEntity = ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    // insert
    @RequestMapping(value = "/tim-peneliti", method = RequestMethod.POST)
    public ResponseEntity postBuku(@RequestBody TimPenelitiDTO timPenelitiDTO) {
        ResponseEntity responseEntity = null;

        try {
            timPenelitiService.save(timPenelitiDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
