package com.pusilkom.base.controller;

import com.pusilkom.base.dto.BeritaDTO;
import com.pusilkom.base.dto.DokumenOrganisasiDTO;
import com.pusilkom.base.service.HomeService;
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
public class HomeController {

    @Autowired
    private HomeService homeService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/berita-terkini/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity getBeritaTerkini(@PathVariable("idOrganisasi") Integer idOrganisasi) {
        ResponseEntity responseEntity = null;
        try {
            List<BeritaDTO> beritaList = homeService.getBeritaTerkini(idOrganisasi);
            responseEntity = ResponseEntity.ok(beritaList);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/dokumen-terkini/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity getDokumenTerkini(@PathVariable("idOrganisasi") Integer idOrganisasi) {
        ResponseEntity responseEntity = null;
        try {
            List<DokumenOrganisasiDTO> dokumenList = homeService.getDokumenTerkini(idOrganisasi);
            responseEntity = ResponseEntity.ok(dokumenList);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
