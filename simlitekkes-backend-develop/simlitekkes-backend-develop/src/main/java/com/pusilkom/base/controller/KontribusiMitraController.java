package com.pusilkom.base.controller;

import com.pusilkom.base.dto.DownloadResponse;
import com.pusilkom.base.dto.KontribusiMitraDTO;
import com.pusilkom.base.model.JenisMitra;
import com.pusilkom.base.model.KontribusiMitra;
import com.pusilkom.base.service.KontribusiMitraService;
import com.pusilkom.base.service.OutputService;
import com.pusilkom.base.util.DebugUtil;
import com.pusilkom.base.util.DownloadUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ars on 2020-05-05 09:47:00.
 */

@RestController
public class KontribusiMitraController {
    @Autowired
    KontribusiMitraService kontribusiMitraService;
    @Autowired
    DebugUtil d;

    Logger log = LoggerFactory.getLogger(this.getClass());

    // insert
    @RequestMapping(value = "/kontribusi-mitra", method = RequestMethod.POST)
    public ResponseEntity postKontribusiMitra(@RequestBody KontribusiMitraDTO kontribusiMitraDTO) {
        ResponseEntity responseEntity = null;

        try {
            kontribusiMitraService.save(kontribusiMitraDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/kontribusi-mitra", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody KontribusiMitraDTO kontribusiMitraDTO) {
        ResponseEntity responseEntity = null;
        try {
            kontribusiMitraService.update(kontribusiMitraDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete by id mitra
    @RequestMapping(value = "/kontribusi-mitra/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteByIdMitra(@PathVariable Integer id, @PathVariable String username) {
        ResponseEntity responseEntity = null;
        try {
            kontribusiMitraService.delete(id, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    // get detail kontribusiMitra by id
    @RequestMapping(value = "/kontribusi-mitra/list/{idMitra}", method = RequestMethod.GET)
    public ResponseEntity getDetailKontribusiMitraByIdOutput(@PathVariable Integer idMitra) {
        ResponseEntity responseEntity = null;
        try {
            List<KontribusiMitra> mitra = kontribusiMitraService.getKontribusiMitraByIdMitra(idMitra);
            responseEntity = ResponseEntity.ok(mitra);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // insert kontribusi mitra
    @RequestMapping(value = "/kontribusi-mitra/list", method = RequestMethod.POST)
    public ResponseEntity postKontribusiMitraBulk(@RequestBody List<KontribusiMitraDTO> kontribusiMitraDTOList) {
        ResponseEntity responseEntity = null;
        try {
            for (KontribusiMitraDTO kontri : kontribusiMitraDTOList) {
                kontribusiMitraService.save(kontri);
            }
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update kontribusi mitra
    @RequestMapping(value = "/kontribusi-mitra/list/{idMitra}", method = RequestMethod.PUT)
    public ResponseEntity putKontribusiMitraBulk(@RequestBody List<KontribusiMitraDTO> kontribusiMitraDTOList, @PathVariable("idMitra") Integer idMitra) {
        ResponseEntity responseEntity = null;
        try {
            List<KontribusiMitra> oldList = kontribusiMitraService.getKontribusiMitraByIdMitra(idMitra);
            String username = ""; Integer idPenelitian = -1;
            for (KontribusiMitra oldKontri : oldList) {
                Boolean isExist = false;
                for (KontribusiMitraDTO kontri : kontribusiMitraDTOList) {
                    username = kontri.getUsername();
                    idPenelitian = kontri.getIdPenelitian();
                    if(oldKontri.getTahun() == kontri.getTahun()  && (kontri.getJumlahKontribusi() != null || kontri.getBarangKontribusi() != null)) {
                        kontri.setId(oldKontri.getId());
                        isExist = true;
                        kontribusiMitraService.update(kontri);
                        kontri.setIsnew(false);
                        break;
                    }
                }
                if (isExist == false) {
                    kontribusiMitraService.deleteByPk(oldKontri.getId(), idPenelitian, username);
                }
            }
            for (KontribusiMitraDTO kontri : kontribusiMitraDTOList) {
                if(kontri.getIsnew() == null && (kontri.getJumlahKontribusi() != null || kontri.getBarangKontribusi() != null)) {
                    kontribusiMitraService.save(kontri);
                }
            }
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
