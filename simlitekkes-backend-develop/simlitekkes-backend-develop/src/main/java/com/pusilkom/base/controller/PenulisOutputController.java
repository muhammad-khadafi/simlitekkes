package com.pusilkom.base.controller;

import com.pusilkom.base.dto.PenulisOutputDTO;
import com.pusilkom.base.model.PeranPenulisOutput;
import com.pusilkom.base.service.PenulisOutputService;
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
 * Created by ars on 2020-03-04 09:47:00.
 */

@RestController
public class PenulisOutputController {
    @Autowired
    PenulisOutputService penulisOutputService;
    Logger log = LoggerFactory.getLogger(this.getClass());

    // insert
    @RequestMapping(value = "/penulis-output", method = RequestMethod.POST)
    public ResponseEntity postPenulisOutput(@RequestBody PenulisOutputDTO penulisOutputDTO) {
        ResponseEntity responseEntity = null;

        try {
            penulisOutputService.save(penulisOutputDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/penulis-output", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody PenulisOutputDTO penulisOutputDTO) {
        ResponseEntity responseEntity = null;
        try {
            penulisOutputService.update(penulisOutputDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/penulis-output/{idOutput}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer idOutput, @PathVariable String username) {
        ResponseEntity responseEntity = null;
        try {
            penulisOutputService.delete(idOutput, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    // get list peran penulis output
    @RequestMapping(value = "/peran-penulis-output/all", method = RequestMethod.GET)
    public ResponseEntity getAllPeranPenulisOutput() {
        ResponseEntity responseEntity = null;
        try {
            List<PeranPenulisOutput> peranPenulisOutputs = penulisOutputService.getAllPeranPenulisOutput();
            responseEntity = ResponseEntity.ok(peranPenulisOutputs);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }
}
