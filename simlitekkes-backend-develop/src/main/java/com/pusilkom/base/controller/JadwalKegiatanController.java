package com.pusilkom.base.controller;

import com.pusilkom.base.dto.BukuDTO;
import com.pusilkom.base.dto.JadwalKegiatanDTO;
import com.pusilkom.base.model.JadwalKegiatan;
import com.pusilkom.base.service.JadwalKegiatanService;
import com.pusilkom.base.util.DebugUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by code-generator on 2020-04-24 09:47:00.
 */

@RestController
public class JadwalKegiatanController {
    @Autowired
    JadwalKegiatanService jadwalKegiatanService;
    @Autowired
    DebugUtil d;

    Logger log = LoggerFactory.getLogger(this.getClass());

    // get list jawal kegiatan by id penelitian
    @RequestMapping(value = "/jadwal-kegiatan/{idPenelitian}/{tahun}", method = RequestMethod.GET)
    public ResponseEntity getAllJenisOutput(@PathVariable Integer idPenelitian, @PathVariable Integer tahun) throws Exception  {
        ResponseEntity responseEntity = null;
        try {
            List<JadwalKegiatan> jadwalKegiatanList = jadwalKegiatanService.getListJadwalKegiatanByIdPenelitian(idPenelitian, tahun);
            responseEntity = ResponseEntity.ok(jadwalKegiatanList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // get list distinct tahun di tbl jadwal_kegiatan
    @RequestMapping(value = "/jadwal-kegiatan/tahun/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getTahunJadwalKegiatan(@PathVariable Integer idPenelitian) throws Exception  {
        ResponseEntity responseEntity = null;
        try {
            List<Integer> listTahunJadwalKegiatan = jadwalKegiatanService.getListTahunJadwalKegiatanByIdPenelitian(idPenelitian);
            responseEntity = ResponseEntity.ok(listTahunJadwalKegiatan);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // insert
    @RequestMapping(value = "/jadwal-kegiatan", method = RequestMethod.POST)
    public ResponseEntity postJadwalKegiatan(@RequestBody JadwalKegiatanDTO jadwalKegiatanDTO) {
        ResponseEntity responseEntity = null;

        try {
            jadwalKegiatanService.save(jadwalKegiatanDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/jadwal-kegiatan", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody JadwalKegiatanDTO jadwalKegiatanDTO) {
        ResponseEntity responseEntity = null;
        try {
            jadwalKegiatanService.update(jadwalKegiatanDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/jadwal-kegiatan/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer id, @PathVariable String username) {
        ResponseEntity responseEntity = null;
        try {
            jadwalKegiatanService.delete(id, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    // get list jawal kegiatan by id penelitian
    @RequestMapping(value = "/jadwal-kegiatan/all/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getAllJadwalKegiatanByIdPenelitian(@PathVariable Integer idPenelitian) {
        ResponseEntity responseEntity = null;
        try {
            List<JadwalKegiatan> jadwalKegiatanList = jadwalKegiatanService.getAllJadwalKegiatanByIdPenelitian(idPenelitian);
            responseEntity = ResponseEntity.ok(jadwalKegiatanList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
