/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.controller;

import com.pusilkom.base.dto.RencanaAnggaranDTO;
import com.pusilkom.base.dto.RencanaAnggaranDTOGroupByYear;
import com.pusilkom.base.dto.RoleDTO;
import com.pusilkom.base.model.KomponenRab;
import com.pusilkom.base.model.SkemaPenelitian;
import com.pusilkom.base.service.RencanaAnggaranService;
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
public class RencanaAnggaranController {
    @Autowired
    RencanaAnggaranService rencanaAnggaranService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/rencana-anggaran/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getData(@PathVariable Integer idPenelitian){
        ResponseEntity responseEntity = null;
        try{
            List<RencanaAnggaranDTOGroupByYear> result = rencanaAnggaranService.getRencanaAnggaran(idPenelitian);
            responseEntity = ResponseEntity.ok(result);
        }
        catch(Exception e){
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/dana-anggaran/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getDanaAnggaran(@PathVariable Integer idPenelitian){
        ResponseEntity responseEntity = null;
        try{
            SkemaPenelitian result = rencanaAnggaranService.getDanaAnggaran(idPenelitian);
            responseEntity = ResponseEntity.ok(result);
        }
        catch(Exception e){
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/rencana-anggaran", method = RequestMethod.POST)
    public ResponseEntity postRencanaAnggaran(@RequestBody RencanaAnggaranDTO rencanaAnggaranDTO) {
        ResponseEntity responseEntity = null;
        try {
            rencanaAnggaranService.saveRencanaAnggaran(rencanaAnggaranDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/rencana-anggaran", method = RequestMethod.PUT)
    public ResponseEntity putRencanaAnggaran(@RequestBody RencanaAnggaranDTO rencanaAnggaranDTO) {
        ResponseEntity responseEntity = null;
        try {
            rencanaAnggaranService.updateRencanaAnggaran(rencanaAnggaranDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/rencana-anggaran/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;

        try {
            rencanaAnggaranService.deleteRencanaAnggaran(id);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/komponen-rab", method = RequestMethod.GET)
    public ResponseEntity getKomponenRab(){
        ResponseEntity responseEntity = null;
        try{
            List<KomponenRab> result = rencanaAnggaranService.getKomponenRab();
            responseEntity = ResponseEntity.ok(result);
        }
        catch(Exception e){
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/rencanaAnggaran/getDataTahun/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getDataTahun(@PathVariable Integer idPenelitian){
        ResponseEntity responseEntity = null;
        try{
            List<RencanaAnggaranDTO> result = rencanaAnggaranService.getTotalBiayaPerTahun(idPenelitian);
            responseEntity = ResponseEntity.ok(result);
        }
        catch(Exception e){
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/rencanaAnggaran/getDetail/{idPenelitian}/{tahun}", method = RequestMethod.GET)
    public ResponseEntity getDetailFromPenelitianTahun(@PathVariable Integer idPenelitian, @PathVariable Integer tahun){
        ResponseEntity responseEntity = null;
        try{
            List<RencanaAnggaranDTO> result = rencanaAnggaranService.getDetailFromPenelitianTahun(idPenelitian, tahun);
            responseEntity = ResponseEntity.ok(result);
        }
        catch(Exception e){
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/rencana-anggaran/get-total-anggaran/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getTotalFromPenelitian(@PathVariable Integer idPenelitian){
        ResponseEntity responseEntity = null;
        try{
            String result = rencanaAnggaranService.getTotalFromPenelitian(idPenelitian);
            responseEntity = ResponseEntity.ok(result);
        }
        catch(Exception e){
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }
}
