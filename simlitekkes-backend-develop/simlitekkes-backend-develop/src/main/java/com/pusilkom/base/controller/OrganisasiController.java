package com.pusilkom.base.controller;

import com.pusilkom.base.dto.OrganisasiDTO;
import com.pusilkom.base.model.Organisasi;
import com.pusilkom.base.service.OrganisasiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class OrganisasiController {

    @Autowired
    private OrganisasiService organisasiService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/profil-poltekkes/{id}", method = RequestMethod.GET)
    public ResponseEntity getProfilPoltekkes(@PathVariable("id") Integer id)  {
        ResponseEntity responseEntity = null;

        try {
            Organisasi organisasi = organisasiService.getProfilPoltekkesByUserId(id);
            responseEntity = ResponseEntity.ok(organisasi);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/profil-poltekkes", method = RequestMethod.PUT)
    public ResponseEntity updateProfilPoltekkes(@RequestBody OrganisasiDTO organisasiDTO) {
        ResponseEntity responseEntity = null;

        try {
            boolean failed = organisasiService.updateProfilPoltekkes(organisasiDTO);
            if (!failed)
                responseEntity = ResponseEntity.ok().build();
            else
                responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/organisasi", method = RequestMethod.GET)
    public ResponseEntity getAllOrganisasi(HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity responseEntity = null;

        try {
            List<Organisasi> organisasiList = organisasiService.getAllOrganisasi();
            responseEntity = ResponseEntity.ok(organisasiList);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/organisasi-list/{organisationId}", method = RequestMethod.GET)
    public ResponseEntity getOrganisasiList(@PathVariable("organisationId") Integer organisationId) {
        ResponseEntity responseEntity = null;
        try {
            List<Organisasi> organisasiList = organisasiService.getOrganisasiList(organisationId);
            responseEntity = ResponseEntity.ok(organisasiList);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value= "/organisasi/{id}", method = RequestMethod.GET)
    public ResponseEntity getOrganisasiById(@PathVariable("id") Integer id) {
        ResponseEntity responseEntity = null;

        try {
            Organisasi organisasi = organisasiService.getOrganisasiById(id);
            responseEntity = ResponseEntity.ok(organisasi);

        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/organisasi", method = RequestMethod.POST)
    public ResponseEntity addOrganisasi(@RequestBody OrganisasiDTO organisasiDTO) {
        ResponseEntity responseEntity = null;

        try {
            boolean failed = organisasiService.addOrganisasi(organisasiDTO);
            if (!failed)
                responseEntity = ResponseEntity.ok().build();
            else
                responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();

        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value= "/organisasi", method = RequestMethod.PUT)
    public ResponseEntity updateOrganisasi(@RequestBody OrganisasiDTO organisasiDTO) {
        ResponseEntity responseEntity = null;

        try {
            boolean failed = organisasiService.updateOrganisasi(organisasiDTO);
            if (!failed)
                responseEntity = ResponseEntity.ok().build();
            else
                responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();

        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value="/organisasi/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrganisasi(@PathVariable("id") Long id, @PathVariable("username") String username) {
        ResponseEntity responseEntity = null;

        try {
            int referred = organisasiService.deleteOrganisasi(id, username);

            if (referred == 0)
                responseEntity = ResponseEntity.ok().build();
            else
                responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();

        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return  responseEntity;
    }

    @RequestMapping(value = "/topik-unggulan/{id}", method = RequestMethod.GET)
    public ResponseEntity getTemaTopikUnggulan(@PathVariable("id") Integer id) {
        ResponseEntity responseEntity = null;
        try {
            OrganisasiDTO organisasiDTO = organisasiService.getTemaTopikUnggulanByUserId(id);
            responseEntity = ResponseEntity.ok(organisasiDTO);

        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value= "/topik-unggulan", method = RequestMethod.PUT)
    public ResponseEntity updateTemaTopikUnggulan(@RequestBody OrganisasiDTO organisasiDTO) {
        ResponseEntity responseEntity = null;

        try {
            organisasiService.updateTemaTopikUnggulan(organisasiDTO);
            responseEntity = ResponseEntity.ok().build();
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value= "/organisasi/kodePddikti/{kodePddikti}", method = RequestMethod.GET)
    public ResponseEntity getOrganisasiByKodePddikti(@PathVariable("kodePddikti") String kodePddikti) {
        ResponseEntity responseEntity = null;

        try {
            Organisasi organisasi = organisasiService.getOrganisasiByKodePddikti(kodePddikti);
            responseEntity = ResponseEntity.ok(organisasi);

        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/organisasi/nonPoltekkes", method = RequestMethod.GET)
    public ResponseEntity getAllOrganisasiNonPoltekkes() {
        ResponseEntity responseEntity = null;

        try {
            List<Organisasi> organisasiList = organisasiService.getAllOrganisasiNonPoltekkes();
            responseEntity = ResponseEntity.ok(organisasiList);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
