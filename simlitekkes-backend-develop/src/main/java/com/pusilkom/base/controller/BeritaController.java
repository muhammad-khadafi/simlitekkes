package com.pusilkom.base.controller;

import com.pusilkom.base.dto.BeritaDTO;
import com.pusilkom.base.dto.Response;
import com.pusilkom.base.model.Berita;
import com.pusilkom.base.service.BeritaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class BeritaController {

    @Autowired
    BeritaService beritaService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/berita/{idUser}/{roleCode}", method = RequestMethod.GET)
    public ResponseEntity getAllBerita(@PathVariable("idUser") Integer idUser, @PathVariable("roleCode") String roleCode) {
        ResponseEntity responseEntity = null;
        try {
            List<BeritaDTO> beritaList = beritaService.getAllBerita(idUser, roleCode);
            responseEntity = ResponseEntity.ok(beritaList);
        }catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/berita", method = RequestMethod.POST)
    public ResponseEntity addBerita(@RequestBody BeritaDTO beritaDTO) {
        ResponseEntity responseEntity = null;
        try {
            beritaService.addBerita(beritaDTO);
            responseEntity = ResponseEntity.ok().build();
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/berita/{id}", method = RequestMethod.GET)
    public ResponseEntity getBeritaById(@PathVariable("id") Integer id) {
        ResponseEntity responseEntity = null;
        try {
            Berita berita = beritaService.getBeritaById(id);
            responseEntity = ResponseEntity.ok(berita);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/berita", method = RequestMethod.PUT)
    public ResponseEntity updateBerita(@RequestBody BeritaDTO beritaDTO) {
        ResponseEntity responseEntity = null;
        try {
            beritaService.updateBerita(beritaDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/berita/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBerita(@PathVariable("id") Integer id, @PathVariable("username") String username) {
        ResponseEntity responseEntity = null;
        try {
            beritaService.deleteBerita(id, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/berita/detail/{idBerita}", method = RequestMethod.GET)
    public ResponseEntity getDetailBeritaById(@PathVariable("idBerita") Integer idBerita) {
        ResponseEntity responseEntity = null;
        try {
            BeritaDTO berita = beritaService.getDetailBeritaById(idBerita);
            responseEntity = ResponseEntity.ok(berita);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
