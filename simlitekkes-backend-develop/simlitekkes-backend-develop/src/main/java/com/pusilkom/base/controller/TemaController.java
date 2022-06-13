package com.pusilkom.base.controller;

import com.pusilkom.base.dto.SkemaPenelitianDTO;
import com.pusilkom.base.dto.TemaDTO;
import com.pusilkom.base.model.KelompokTkt;
import com.pusilkom.base.model.Organisasi;
import com.pusilkom.base.service.SkemaPenelitianService;
import com.pusilkom.base.service.TemaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by code-generator on 2020-03-11 09:47:00.
 */

@RestController
public class TemaController {

    @Autowired
    TemaService temaService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    // get all tema
    @RequestMapping(value = "/tema", method = RequestMethod.GET)
    public ResponseEntity getTema() {
        ResponseEntity responseEntity = null;
        TemaDTO search = new TemaDTO();
        try {
            List<TemaDTO> temaDTOList = temaService.getListTema(search);
            responseEntity = ResponseEntity.ok(temaDTOList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    //get tema by id
    @RequestMapping(value = "/tema/{id}", method = RequestMethod.GET)
    public ResponseEntity getTemaById(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;
        TemaDTO search = new TemaDTO();
        search.setId(id);
        try {
            List<TemaDTO> temaDTOList = temaService.getListTema(search);
            if(temaDTOList != null && temaDTOList.size()>0) {
                search = temaDTOList.get(0);
            }
            responseEntity = ResponseEntity.ok(search);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // insert
    @RequestMapping(value = "/tema", method = RequestMethod.POST)
    public ResponseEntity postTema(@RequestBody TemaDTO temaDTO) {
        ResponseEntity responseEntity = null;

        try {
            temaService.save(temaDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/tema", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody TemaDTO temaDTO) {
        ResponseEntity responseEntity = null;
        try {
            temaService.update(temaDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/tema/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer id, @PathVariable String username) {
        ResponseEntity responseEntity = null;
        try {
            temaService.delete(id, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //get all kelompok tkt
    @RequestMapping(value = "/kelompok-tkt/all", method = RequestMethod.GET)
    public ResponseEntity getAllKelompokTkt() {
        ResponseEntity responseEntity = null;

        try {
            List<KelompokTkt> kelompokTktList = temaService.getAllKelompokTkt();
            responseEntity = ResponseEntity.ok(kelompokTktList);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
