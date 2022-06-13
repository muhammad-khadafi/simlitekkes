/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.controller;

import com.pusilkom.base.dto.BukuDTO;
import com.pusilkom.base.dto.PenelitianDTO;
import com.pusilkom.base.model.Penelitian;
import com.pusilkom.base.model.StatusTahapPenelitian;
import com.pusilkom.base.service.PenelitianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 * @author Helmi Fakhriandi
 */
@RestController
public class PenelitianController {

    @Autowired
    PenelitianService penelitianService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/usulanList/{idUser}", method = RequestMethod.GET)
    public ResponseEntity getUsulan(@PathVariable Integer idUser){
        ResponseEntity responseEntity = null;
        try {
            List<PenelitianDTO> usulanList = penelitianService.getListUsulan(idUser);
            responseEntity = ResponseEntity.ok(usulanList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }
    @RequestMapping(value = "/penelitian/riwayat-usulan/{idUser}", method = RequestMethod.GET)
    public ResponseEntity getRiwayatUsulan(@PathVariable Integer idUser){
        ResponseEntity responseEntity = null;
        try {
            List<PenelitianDTO> usulanList = penelitianService.getListUsulanRiwayat(idUser);
            responseEntity = ResponseEntity.ok(usulanList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/penelitian/getRekap/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity getRekap(@PathVariable Integer idOrganisasi){
        ResponseEntity responseEntity = null;
        try {
            List<PenelitianDTO> rekapList = penelitianService.getRekap(idOrganisasi);
            responseEntity = ResponseEntity.ok(rekapList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/penelitian/getDaftarBySkemaTahun/{idOrganisasi}/{idSkemaPenelitian}/{tahunUsulan}/{tahunMulai}/{tahunSelesai}", method = RequestMethod.GET)
    public ResponseEntity getRekap(@PathVariable Integer idOrganisasi, @PathVariable Integer idSkemaPenelitian, @PathVariable String tahunUsulan, @PathVariable String tahunMulai, @PathVariable String tahunSelesai){
        ResponseEntity responseEntity = null;
        try {
            List<PenelitianDTO> response = penelitianService.getDaftarBySkemaTahun(idOrganisasi, idSkemaPenelitian, tahunUsulan, tahunMulai, tahunSelesai);
            responseEntity = ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/penelitian/{id}", method = RequestMethod.GET)
    public ResponseEntity getPenelitianById(@PathVariable Integer id){
        ResponseEntity responseEntity = null;
        try {
            Penelitian response = penelitianService.getPenelitianById(id);
            responseEntity = ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/penelitian", method = RequestMethod.PUT)
    public ResponseEntity updatePenelitian(@RequestBody PenelitianDTO penelitianDTO){
        ResponseEntity responseEntity = null;
        try{
            penelitianService.updatePenelitian(penelitianDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/penelitian/listByIdPeneliti/{idUser}", method = RequestMethod.GET)
    public ResponseEntity getListByIdPeneliti(@PathVariable Integer idUser){
        ResponseEntity responseEntity = null;
        try {
            List<PenelitianDTO> response = penelitianService.getListByIdPeneliti(idUser);
            responseEntity = ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/penelitian/detailStatus/{id}", method = RequestMethod.GET)
    public ResponseEntity getPenelitianDetailStatusById(@PathVariable Integer id){
        ResponseEntity responseEntity = null;
        try {
            PenelitianDTO response = penelitianService.getPenelitianDetailStatusById(id);
            responseEntity = ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/penelitian/getStatusBaru/{idStatusSekarang}/{role}", method = RequestMethod.GET)
    public ResponseEntity getStatusTahapanPenelitianBaru(@PathVariable Integer idStatusSekarang, @PathVariable String role, @RequestParam(value = "kodeStatus", required = false) String kodeStatus){
        ResponseEntity responseEntity = null;
        try {
            System.out.println("check kode status: " + kodeStatus);
            if (kodeStatus == null) kodeStatus = "";
            StatusTahapPenelitian response = penelitianService.getStatusTahapanPenelitianBaru(idStatusSekarang, role, kodeStatus);
            responseEntity = ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    // insert
    @RequestMapping(value = "/penelitian", method = RequestMethod.POST)
    public ResponseEntity postBuku(@RequestBody PenelitianDTO penelitianDTO) {
        ResponseEntity responseEntity = null;

        try {
            Integer idPenelitian = penelitianService.save(penelitianDTO);
            responseEntity = ResponseEntity.ok(idPenelitian);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/penelitian/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBerita(@PathVariable("id") Integer id, @PathVariable("username") String username) {
        ResponseEntity responseEntity = null;
        try {
            penelitianService.deleteRiwayatPenelitian(id, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
