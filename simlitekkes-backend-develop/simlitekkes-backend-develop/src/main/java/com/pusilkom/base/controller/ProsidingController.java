package com.pusilkom.base.controller;

import com.pusilkom.base.dto.ProsidingDTO;
import com.pusilkom.base.model.JenisOutput;
import com.pusilkom.base.service.OutputService;
import com.pusilkom.base.service.ProsidingService;
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
 * Created by code-generator on 2020-04-14 09:47:00.
 */

@RestController
public class ProsidingController {
    @Autowired
    ProsidingService prosidingService;
    @Autowired
    OutputService outputService;
    @Autowired
    DebugUtil d;

    Logger log = LoggerFactory.getLogger(this.getClass());

    // get all prosiding
    @RequestMapping(value = "/prosiding/{idUser}", method = RequestMethod.GET)
    public ResponseEntity getListProsiding(@PathVariable Integer idUser) {
        ResponseEntity responseEntity = null;
        ProsidingDTO search = new ProsidingDTO();
        search.setIdUser(idUser);
        try {
            List<ProsidingDTO> dtoList = prosidingService.getListProsidingDTO(search);
            responseEntity = ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // insert
    @RequestMapping(value = "/prosiding", method = RequestMethod.POST)
    public ResponseEntity postProsiding(@RequestBody ProsidingDTO prosidingDTO) {
        ResponseEntity responseEntity = null;

        try {
            prosidingService.save(prosidingDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/prosiding", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody ProsidingDTO prosidingDTO) {
        ResponseEntity responseEntity = null;
        try {
            prosidingService.update(prosidingDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/prosiding/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer id, @PathVariable String username) {
        ResponseEntity responseEntity = null;
        try {
            prosidingService.delete(id, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    // get detail prosiding by id
    @RequestMapping(value = "/prosiding/detail/{idOutput}", method = RequestMethod.GET)
    public ResponseEntity getDetailProsidingByIdOutput(@PathVariable Integer idOutput) {
        ResponseEntity responseEntity = null;
        ProsidingDTO search = new ProsidingDTO();
        search.setIdOutput(idOutput);
        try {
            List<ProsidingDTO> dtoList = prosidingService.getListProsidingDTO(search);
            responseEntity = ResponseEntity.ok(dtoList.get(0));
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // get all jenis output prosiding
    @RequestMapping(value = "/jenis-output/prosiding", method = RequestMethod.GET)
    public ResponseEntity getAllJenisOutput() {
        ResponseEntity responseEntity = null;
        try {
            List<JenisOutput> jenisOutputs = outputService.getAllJenisOutput("prosiding");
            responseEntity = ResponseEntity.ok(jenisOutputs);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    //get file prosiding
    @RequestMapping(value = "/prosiding-file/{id}", method = RequestMethod.GET)
    public ResponseEntity getProsidingFile(@PathVariable Integer id) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            String fileEncoded = outputService.getFile(id);
            responseEntity = ResponseEntity.ok(fileEncoded);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    //delete file prosiding
    @RequestMapping(value = "/prosiding-file/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProsidingFile(@PathVariable Integer id) {
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
