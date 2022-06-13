package com.pusilkom.base.controller;

import com.pusilkom.base.dto.JurnalDTO;
import com.pusilkom.base.model.JenisOutput;
import com.pusilkom.base.service.JurnalService;
import com.pusilkom.base.service.OutputService;
import com.pusilkom.base.util.DebugUtil;
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
public class JurnalController {
    @Autowired
    JurnalService jurnalService;
    @Autowired
    OutputService outputService;
    @Autowired
    DebugUtil d;

    Logger log = LoggerFactory.getLogger(this.getClass());

    // get all jurnal
    @RequestMapping(value = "/jurnal/{idUser}", method = RequestMethod.GET)
    public ResponseEntity getListJurnal(@PathVariable Integer idUser) {
        ResponseEntity responseEntity = null;
        JurnalDTO search = new JurnalDTO();
        search.setIdUser(idUser);
        try {
            List<JurnalDTO> dtoList = jurnalService.getListJurnal(search);
            responseEntity = ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // insert
    @RequestMapping(value = "/jurnal", method = RequestMethod.POST)
    public ResponseEntity postJurnal(@RequestBody JurnalDTO jurnalDTO) {
        ResponseEntity responseEntity = null;

        try {
            jurnalService.save(jurnalDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/jurnal", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody JurnalDTO jurnalDTO) {
        ResponseEntity responseEntity = null;
        try {
            jurnalService.update(jurnalDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/jurnal/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer id, @PathVariable String username) {
        ResponseEntity responseEntity = null;
        try {
            jurnalService.delete(id, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    // get detail jurnal by id
    @RequestMapping(value = "/jurnal/detail/{idOutput}", method = RequestMethod.GET)
    public ResponseEntity getDetailJurnalByIdOutput(@PathVariable Integer idOutput) {
        ResponseEntity responseEntity = null;
        JurnalDTO search = new JurnalDTO();
        search.setIdOutput(idOutput);
        try {
            List<JurnalDTO> dtoList = jurnalService.getListJurnal(search);
            responseEntity = ResponseEntity.ok(dtoList.get(0));
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // get all jenis output jurnal
    @RequestMapping(value = "/jenis-output/jurnal", method = RequestMethod.GET)
    public ResponseEntity getAllJenisOutput() {
        ResponseEntity responseEntity = null;
        try {
            List<JenisOutput> jenisOutputs = outputService.getAllJenisOutput("jurnal");
            responseEntity = ResponseEntity.ok(jenisOutputs);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    //get file jurnal
    @RequestMapping(value = "/jurnal-file/{id}", method = RequestMethod.GET)
    public ResponseEntity getJurnalFile(@PathVariable Integer id) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            String fileEncoded = outputService.getFile(id);
            responseEntity = ResponseEntity.ok(fileEncoded);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    //delete file jurnal
    @RequestMapping(value = "/jurnal-file/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteJurnalFile(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;

        try {
            outputService.deleteFile(id);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
