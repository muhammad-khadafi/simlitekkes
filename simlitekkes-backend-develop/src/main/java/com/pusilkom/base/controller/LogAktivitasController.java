package com.pusilkom.base.controller;

import com.pusilkom.base.dto.BeritaDTO;
import com.pusilkom.base.dto.LogAktifitasDTO;
import com.pusilkom.base.service.LogAktivitasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogAktivitasController {

    @Autowired
    LogAktivitasService logAktivitasService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/log-aktifitas/{periode}", method = RequestMethod.GET)
    public ResponseEntity getLogAktifitasPerBulan(@PathVariable("periode") String periode) {
        ResponseEntity responseEntity = null;
        try {
            List<LogAktifitasDTO> logAktifitasList = logAktivitasService.getLogAktifitasPerBulan(periode);
            responseEntity = ResponseEntity.ok(logAktifitasList);
        }catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
