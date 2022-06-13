package com.pusilkom.base.controller;

import com.pusilkom.base.dto.*;
import com.pusilkom.base.model.Organisasi;
import com.pusilkom.base.model.RumpunIlmu;
import com.pusilkom.base.model.SkemaPenelitian;
import com.pusilkom.base.model.TimPeneliti;
import com.pusilkom.base.service.IdentitasPengusulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by muhammad.khadafi on 2020-03-11 08:50:51.
 */

@RestController
public class IdentitasPengusulController {

    @Autowired
    IdentitasPengusulService identitasPengusulService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/skema-penelitian/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getMinMaxTkt(@PathVariable Integer idPenelitian) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            SkemaPenelitian skemaPenelitian = identitasPengusulService.getMinMaxTkt(idPenelitian);
            responseEntity = ResponseEntity.ok(skemaPenelitian);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/pengukuran-tkt/{idJenisTkt}/{activeLvl}", method = RequestMethod.GET)
    public ResponseEntity getTktData(@PathVariable Integer idJenisTkt, @PathVariable Integer activeLvl) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            PengukuranTktDTO pengukuranTktDTO = identitasPengusulService.getTktData(idJenisTkt, activeLvl);
            responseEntity = ResponseEntity.ok(pengukuranTktDTO);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/rumpun-ilmu/{idParent}", method = RequestMethod.GET)
    public ResponseEntity getRumpunIlmu(@PathVariable Integer idParent) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<RumpunIlmu> rumpunIlmu = identitasPengusulService.getRumpunIlmu(idParent);
            responseEntity = ResponseEntity.ok(rumpunIlmu);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/lama-kegiatan/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getLamaKegiatan(@PathVariable Integer idPenelitian) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<LamaKegiatanDto> lamaKegiatan = identitasPengusulService.getLamaKegiatan(idPenelitian);
            responseEntity = ResponseEntity.ok(lamaKegiatan);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/identitas-usulan/{idPenelitian}", method = RequestMethod.PUT)
    public ResponseEntity putIdentitasUsulan(@PathVariable Integer idPenelitian, @RequestBody PenelitianDTO penelitianDTO) {
        ResponseEntity responseEntity = null;
        try {
            identitasPengusulService.putIdentitasUsulan(idPenelitian, penelitianDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/identitas-usulan/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getIdentitasUsulan(@PathVariable Integer idPenelitian) {
        ResponseEntity responseEntity = null;

        try {
            PenelitianDTO penelitianDTO = identitasPengusulService.getIdentitasUsulan(idPenelitian);
            responseEntity = ResponseEntity.ok(penelitianDTO);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/skema-penelitian-by-penelitian/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getSkemaPenelitianByIdPenelitian(@PathVariable Integer idPenelitian) {
        ResponseEntity responseEntity = null;

        try {
            SkemaPenelitian skemaPenelitian = identitasPengusulService.getSkemaPenelitianByIdPenelitian(idPenelitian);
            responseEntity = ResponseEntity.ok(skemaPenelitian);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/anggota-peneliti/{isPoltekkes}/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getAnggotaPeneliti(@PathVariable Boolean isPoltekkes, @PathVariable Integer idPenelitian) {
        ResponseEntity responseEntity = null;

        try {
            List<PenelitiDTO> penelitiDTO = identitasPengusulService.getAnggotaPeneliti(isPoltekkes, idPenelitian);
            responseEntity = ResponseEntity.ok(penelitiDTO);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/identitas-usulan/preview/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getIdentitasUsulanPreview(@PathVariable Integer idPenelitian) {
        ResponseEntity responseEntity = null;

        try {
            IdentitasUsulanPreviewDTO dto = identitasPengusulService.getIdentitasUsulanPreview(idPenelitian);
            responseEntity = ResponseEntity.ok(dto);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/anggota-peneliti/{idPenelitian}/{tugasPenelitian}/{alokasiJam}", method = RequestMethod.POST)
    public ResponseEntity postAnggotaPeneliti(@PathVariable Integer idPenelitian, @PathVariable String tugasPenelitian, @PathVariable BigDecimal alokasiJam, @RequestBody List<PenelitiDTO> penelitiDTOList) {
        ResponseEntity responseEntity = null;

        try {
            identitasPengusulService.addAnggotaPeneliti(idPenelitian, tugasPenelitian, alokasiJam, penelitiDTOList);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/tim-peneliti/{idPenelitian}/{isPoltekkes}", method = RequestMethod.GET)
    public ResponseEntity getTimPeneliti(@PathVariable Integer idPenelitian, @PathVariable Boolean isPoltekkes) {
        ResponseEntity responseEntity = null;

        try {
            List<PenelitiDTO> penelitiDTO = identitasPengusulService.getTimPeneliti(idPenelitian, isPoltekkes);
            responseEntity = ResponseEntity.ok(penelitiDTO);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/anggota-peneliti/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTimPeneliti(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;

        try {
            identitasPengusulService.deleteTimPeneliti(id);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/organisasi-non-poltekkes", method = RequestMethod.GET)
    public ResponseEntity getOrganisasiNonPoltekkes() {
        ResponseEntity responseEntity = null;

        try {
            List<Organisasi> organisasiList = identitasPengusulService.getOrganisasiNonPoltekkes();
            responseEntity = ResponseEntity.ok(organisasiList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/bidang-keahlian", method = RequestMethod.GET)
    public ResponseEntity getBidangKeahlian() {
        ResponseEntity responseEntity = null;

        try {
            List<RumpunIlmu> rumpunIlmus = identitasPengusulService.getBidangKeahlian();
            responseEntity = ResponseEntity.ok(rumpunIlmus);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/anggota-peneliti-tidak-terdaftar/{idPenelitian}", method = RequestMethod.POST)
    public ResponseEntity postAnggotaPenelitiTidakTerdaftar(@PathVariable Integer idPenelitian,  @RequestBody PenelitiDTO penelitiDTO) {
        ResponseEntity responseEntity = null;

        try {
            identitasPengusulService.addAnggotaPeneliti(idPenelitian, penelitiDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/tim-peneliti/anggota/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getAnggotaTimPeneliti(@PathVariable Integer idPenelitian) {
        ResponseEntity responseEntity = null;

        try {
            List<TimPeneliti> listTimPeneliti = identitasPengusulService.getAnggotaTimPeneliti(idPenelitian);
            responseEntity = ResponseEntity.ok(listTimPeneliti);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }
}
