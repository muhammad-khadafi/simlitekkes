/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.controller;

import com.pusilkom.base.dto.TargetOutputPenelitianDTO;
import com.pusilkom.base.service.TargetOutputPenelitianService;
import com.pusilkom.base.service.ValidasiService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Helmi Fakhriandi
 */
@RestController
public class TargetOutputPenelitianController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    TargetOutputPenelitianService targetOutputPenelitianService;
    @Autowired
    ValidasiService validasiService;

    @RequestMapping(value = "/target-output-penelitian/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getListTargetOutputPenelitian(@PathVariable Integer idPenelitian) {
        ResponseEntity responseEntity = null;
        try {
            List<TargetOutputPenelitianDTO> response = targetOutputPenelitianService.getListTargetOutputPenelitian(idPenelitian);
            responseEntity = ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/target-output-penelitian/{idPenelitian}/{idOrganisasi}/{roleCode}/{idUser}", method = RequestMethod.GET)
    public ResponseEntity getListTargetOutputPenelitian(@PathVariable("idPenelitian") Integer idPenelitian,
            @PathVariable("idOrganisasi") Integer idOrganisasi,
            @PathVariable("roleCode") String roleCode,
            @PathVariable("idUser") Integer idUser) {
        ResponseEntity responseEntity = null;
        try {
            List<TargetOutputPenelitianDTO> response = new ArrayList();
            Boolean validasiResult = false;
            if (roleCode.equalsIgnoreCase("KAPUS_PPM")) {
                validasiResult = validasiService.getDataValidasiUserFromPoltekkes(idOrganisasi)
                        && validasiService.getDataValidasiIsPenelitianSubmittedD14R(idPenelitian, idOrganisasi, "0");
            } else if (roleCode.equalsIgnoreCase("PENGELOLA_PUSAT")) {
                validasiResult = validasiService.getDataValidasiUserFromPpsdm(idOrganisasi)
                        && validasiService.getDataValidasiIsPenelitianSubmittedF14R(idPenelitian, "0");
            } else if (roleCode.equalsIgnoreCase("REVIEWER")) {
     /*           validasiResult = validasiService.getDataValidasiReviewerHasPenugasan(idUser)
                        && validasiService.getDataValidasiIsPenelitianAssignedI14R(idOrganisasi, "0");*/
                validasiResult = validasiService.getDataValidasiIsPenelitianI14W(idPenelitian, idUser);
            } else if (roleCode.equalsIgnoreCase("DOSEN")) {
                validasiResult = true;
            }

            if (validasiResult) {
                response = targetOutputPenelitianService.getListTargetOutputPenelitian(idPenelitian);
            }
            responseEntity = ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/target-output-penelitian", method = RequestMethod.POST)
    public ResponseEntity insertTargetOutputPenelitian(@RequestBody TargetOutputPenelitianDTO targetOutputPenelitianDTO) {
        ResponseEntity responseEntity = null;
        try {
            targetOutputPenelitianService.insertTargetOutputPenelitian(targetOutputPenelitianDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/target-output-penelitian", method = RequestMethod.PUT)
    public ResponseEntity updateTargetOutputPenelitian(@RequestBody TargetOutputPenelitianDTO targetOutputPenelitianDTO) {
        ResponseEntity responseEntity = null;
        try {
            targetOutputPenelitianService.updateTargetOutputPenelitian(targetOutputPenelitianDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/target-output-penelitian/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTargetOutputPenelitian(@PathVariable Integer id, @PathVariable String username) {
        ResponseEntity responseEntity = null;
        try {
            targetOutputPenelitianService.deleteTargetOutputPenelitian(id, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/validasi-luaran-wajib/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity validasiLuaranWajib(@PathVariable Integer idPenelitian) {
        ResponseEntity responseEntity = null;
        try {
            Boolean response = targetOutputPenelitianService.validasiLuaranWajib(idPenelitian);
            responseEntity = ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
