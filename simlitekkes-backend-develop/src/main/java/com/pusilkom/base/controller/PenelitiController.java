package com.pusilkom.base.controller;

import com.pusilkom.base.dto.*;
import com.pusilkom.base.model.RumpunIlmu;
import com.pusilkom.base.service.PenelitiService;
import com.pusilkom.base.util.DebugUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by code-generator on 2020-03-11 09:47:00.
 */

@RestController
public class PenelitiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PenelitiController.class);

    @Autowired
    private PenelitiService penelitiService;
    @Autowired
    private DebugUtil d;
    Logger log = LoggerFactory.getLogger(this.getClass());

    // get all peneliti
    @RequestMapping(value = "/peneliti", method = RequestMethod.GET)
    public ResponseEntity getListPeneliti() {
        ResponseEntity responseEntity = null;
        PenelitiDTO search = new PenelitiDTO();
        try {
            List<PenelitiDTO> penelitiDTOList = penelitiService.getListPenelitiDTO(search);
            responseEntity = ResponseEntity.ok(penelitiDTOList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    //get peneliti by id
    @RequestMapping(value = "/peneliti/{id}", method = RequestMethod.GET)
    public ResponseEntity getPenelitiById(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;
        PenelitiDTO search = new PenelitiDTO();
        search.setId(id);
        try {
            List<PenelitiDTO> penelitiDTOList = penelitiService.getListPenelitiDTO(search);
            if (penelitiDTOList != null && !penelitiDTOList.isEmpty()) {
                search = penelitiDTOList.get(0);
            } else {
                search.setId(null);
            }
            responseEntity = ResponseEntity.ok(search);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // insert dosen
    @RequestMapping(value = "/peneliti", method = RequestMethod.POST)
    public ResponseEntity insert(@RequestBody PenelitiDTO penelitiDTO) {
        ResponseEntity responseEntity = null;

        try {
            Integer idPeneliti = penelitiService.save(penelitiDTO);
            responseEntity = ResponseEntity.ok(idPeneliti);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update dosen by id
    @RequestMapping(value = "/peneliti", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody PenelitiDTO penelitiDTO) {
        ResponseEntity responseEntity = null;

        try {
            penelitiService.update(penelitiDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // get all bidang keahlian
    @RequestMapping(value = "/bidang-keahlian/all", method = RequestMethod.GET)
    public ResponseEntity getListBidangKeahlian() {
        ResponseEntity responseEntity = null;
        try {
            List<RumpunIlmu> rumpunIlmus = penelitiService.getAllBidangKeahlian();
            responseEntity = ResponseEntity.ok(rumpunIlmus);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    //get peneliti by id user
    @RequestMapping(value = "/peneliti/user/{id}", method = RequestMethod.GET)
    public ResponseEntity getPenelitiByIdUser(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;
        PenelitiDTO search = new PenelitiDTO();
        search.setIdUser(id);
        try {
            List<PenelitiDTO> penelitiDTOList = penelitiService.getListPenelitiDTO(search);
            if (penelitiDTOList != null && !penelitiDTOList.isEmpty()) {
                search = penelitiDTOList.get(0);
            }
            responseEntity = ResponseEntity.ok(search);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // get all peneliti filtered by id organisasi
    @RequestMapping(value = "/peneliti/organisasi/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity getListPenelitiByIdOrganisasi(@PathVariable("idOrganisasi") Integer idOrganisasi) {
        ResponseEntity responseEntity = null;
        PenelitiDTO search = new PenelitiDTO();
        search.setIdOrganisasi(idOrganisasi);
        try {
            List<PenelitiDTO> penelitiDTOList = penelitiService.getListPenelitiDTO(search);
            responseEntity = ResponseEntity.ok(penelitiDTOList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/peneliti-reviewer-internal/{idOrganisasi}/{kataKunci}", method = RequestMethod.GET)
    public ResponseEntity getDaftarPenelitiReviewerInternal(@PathVariable("idOrganisasi") Integer idOrganisasi, @PathVariable("kataKunci") String kataKunci , HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity responseEntity = null;
        try {
            List<PenelitiDTO> daftarPeneliti = penelitiService.getDaftarPenelitiReviewerInternal(idOrganisasi, kataKunci);
            responseEntity = ResponseEntity.ok(daftarPeneliti);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  responseEntity;
    }

    @RequestMapping(value = "/peneliti-reviewer-eksternal/{kataKunci}", method = RequestMethod.GET)
    public ResponseEntity getDaftarPenelitiReviewerEksternal(@PathVariable("kataKunci") String kataKunci, HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity responseEntity = null;
        try {
            List<PenelitiDTO> daftarPeneliti = penelitiService.getDaftarPenelitiReviewerEksternal(kataKunci);
            responseEntity = ResponseEntity.ok(daftarPeneliti);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  responseEntity;
    }

    @RequestMapping(value = "/peneliti/dikti/{nidn}/{token}", method = RequestMethod.GET)
    public ResponseEntity getPenelitiDiktiByNidn(@PathVariable("nidn") String nidn, @PathVariable("token") String token) {
        ResponseEntity responseEntity = null;
        LOGGER.info(">>> string nidn : {}", d.toString(nidn));
        LOGGER.info(">>> string token : {}", d.toString(token));
        PenelitiParentDiktiDTO penelitiDiktiDTO = new PenelitiParentDiktiDTO();
        try {
            penelitiDiktiDTO = penelitiService.getPenelitiDiktiByNidn(nidn, token);
            responseEntity = ResponseEntity.ok(penelitiDiktiDTO);
        } catch (IOException e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/peneliti/dikti/login", method = RequestMethod.GET)
    public ResponseEntity getPenelitiDiktiByNidn() {
        ResponseEntity responseEntity = null;
        try {
            String token = penelitiService.loginPenelitiDikti();
            responseEntity = ResponseEntity.ok(token);
        } catch (IOException e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/perguruan-tinggi/dikti/{token}/{idPt}", method = RequestMethod.GET)
    public ResponseEntity getPerguruanTinggiDiktiByIdPt(@PathVariable("token") String token, @PathVariable("idPt") String idPt) {
        ResponseEntity responseEntity = null;
        LOGGER.info(">>> string nidn : {}", d.toString(token));
        LOGGER.info(">>> string idpt : {}", d.toString(idPt));
        PerguruanTinggiParentDiktiDTO perguruanTinggiParentDiktiDTO = new PerguruanTinggiParentDiktiDTO();
        try {
            perguruanTinggiParentDiktiDTO = penelitiService.getPerguruanTinggiDiktiByIdPt(token, idPt);
            responseEntity = ResponseEntity.ok(perguruanTinggiParentDiktiDTO);
        } catch (IOException e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/prodi/dikti/{token}/{idPt}/{idProdi}", method = RequestMethod.GET)
    public ResponseEntity getProdiDiktiByIdPtDanIdProdi(@PathVariable("token") String token, @PathVariable("idPt") String idPt, @PathVariable("idProdi") String idProdi) {
        ResponseEntity responseEntity = null;
        LOGGER.info(">>> string nidn : {}", d.toString(token));
        LOGGER.info(">>> string idpt : {}", d.toString(idPt));
        LOGGER.info(">>> string idprodi : {}", d.toString(idProdi));
        ProdiParentDiktiDTO prodiParentDiktiDTO = new ProdiParentDiktiDTO();
        try {
            prodiParentDiktiDTO = penelitiService.getProdiDiktiByIdPtDanIdProdi(token, idPt, idProdi);
            responseEntity = ResponseEntity.ok(prodiParentDiktiDTO);
        } catch (IOException e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }
}
