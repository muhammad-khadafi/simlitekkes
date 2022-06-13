/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.controller;

import com.pusilkom.base.dto.JenisOutputDTO;
import com.pusilkom.base.service.JenisOutputService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Helmi Fakhriandi
 */
@RestController
public class JenisOutputController {

    @Autowired
    JenisOutputService jenisOutputService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/jenis-output", method = RequestMethod.GET)
    public ResponseEntity getAll(){
        ResponseEntity responseEntity = null;
        try {
            List<JenisOutputDTO> list = jenisOutputService.getAll();
            responseEntity = ResponseEntity.ok(list);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/jenis-output/list-wajib/{idSkemaPenelitian}/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getAll(@PathVariable Integer idSkemaPenelitian, @PathVariable Integer idPenelitian){
        ResponseEntity responseEntity = null;
        try {
            List<JenisOutputDTO> list = jenisOutputService.getListWajib(idSkemaPenelitian, idPenelitian);
            responseEntity = ResponseEntity.ok(list);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/jenis-output/list-tambahan/{idSkemaPenelitian}/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getAllTambahan( @PathVariable Integer idSkemaPenelitian, @PathVariable Integer idPenelitian){
        ResponseEntity responseEntity = null;
        try {
            List<JenisOutputDTO> list = jenisOutputService.getListWajib(idSkemaPenelitian, idPenelitian);
            responseEntity = ResponseEntity.ok(list);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
