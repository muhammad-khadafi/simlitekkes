package com.pusilkom.base.controller;

import com.pusilkom.base.dto.HkiDTO;
import com.pusilkom.base.model.JenisOutput;
import com.pusilkom.base.service.HkiService;
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
 * Created by code-generator on 2020-04-09 09:47:00.
 */

@RestController
public class HkiController {
    @Autowired
    HkiService hkiService;
    @Autowired
    OutputService outputService;
    @Autowired
    DebugUtil d;

    Logger log = LoggerFactory.getLogger(this.getClass());

    // get all hki
    @RequestMapping(value = "/hki/{idUser}", method = RequestMethod.GET)
    public ResponseEntity getListHki(@PathVariable Integer idUser) {
        ResponseEntity responseEntity = null;
        HkiDTO search = new HkiDTO();
        search.setIdUser(idUser);
        try {
            List<HkiDTO> dtoList = hkiService.getListHkiDTO(search);
            responseEntity = ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // insert
    @RequestMapping(value = "/hki", method = RequestMethod.POST)
    public ResponseEntity postHki(@RequestBody HkiDTO hkiDTO) {
        ResponseEntity responseEntity = null;

        try {
            hkiService.save(hkiDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/hki", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody HkiDTO hkiDTO) {
        ResponseEntity responseEntity = null;
        try {
            hkiService.update(hkiDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/hki/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer id, @PathVariable String username) {
        ResponseEntity responseEntity = null;
        try {
            hkiService.delete(id, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    // get detail hki by id
    @RequestMapping(value = "/hki/detail/{idOutput}", method = RequestMethod.GET)
    public ResponseEntity getDetailHkiByIdOutput(@PathVariable Integer idOutput) {
        ResponseEntity responseEntity = null;
        HkiDTO search = new HkiDTO();
        search.setIdOutput(idOutput);
        try {
            List<HkiDTO> dtoList = hkiService.getListHkiDTO(search);
            responseEntity = ResponseEntity.ok(dtoList.get(0));
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // get all jenis output hki
    @RequestMapping(value = "/jenis-output/hki", method = RequestMethod.GET)
    public ResponseEntity getAllJenisOutput() {
        ResponseEntity responseEntity = null;
        try {
            List<JenisOutput> jenisOutputs = outputService.getAllJenisOutput("hki");
            responseEntity = ResponseEntity.ok(jenisOutputs);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    //get file hki
    @RequestMapping(value = "/hki-file/{id}", method = RequestMethod.GET)
    public ResponseEntity getHkiFile(@PathVariable Integer id) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            String fileEncoded = outputService.getFile(id);
            responseEntity = ResponseEntity.ok(fileEncoded);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    //delete file hki
    @RequestMapping(value = "/hki-file/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteHkiFile(@PathVariable Integer id) {
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
