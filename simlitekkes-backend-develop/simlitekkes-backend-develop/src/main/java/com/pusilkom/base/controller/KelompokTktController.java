package com.pusilkom.base.controller;

import com.pusilkom.base.dto.KelompokTktDTO;
import com.pusilkom.base.dto.MenuDTO;
import com.pusilkom.base.dto.RoleDTO;
import com.pusilkom.base.helper.ResponseEntityHelper;
import com.pusilkom.base.model.KelompokTkt;
import com.pusilkom.base.model.Role;
import com.pusilkom.base.service.KelompokTktService;
import com.pusilkom.base.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by muhammad.khadafi on 2020-03-11 08:50:51.
 */

@RestController
public class KelompokTktController {

    @Autowired
    KelompokTktService kelompokTktService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/jenis-tkt", method = RequestMethod.GET)
    public ResponseEntity getJenisTkt(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<KelompokTkt> kelompokTktList = kelompokTktService.getKelompokTktList();
            responseEntity = ResponseEntity.ok(kelompokTktList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/jenis-tkt", method = RequestMethod.POST)
    public ResponseEntity postJenisTkt(@RequestBody KelompokTktDTO kelompokTktDTO) {
        ResponseEntity responseEntity = null;

        try {
            kelompokTktService.saveKelompokTkt(kelompokTktDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/jenis-tkt", method = RequestMethod.PUT)
    public ResponseEntity putJenisTkt(@RequestBody KelompokTktDTO kelompokTktDTO) {
        ResponseEntity responseEntity = null;

        try {
            kelompokTktService.updateKelompokTkt(kelompokTktDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/jenis-tkt/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteKelompokTkt(@PathVariable Integer id, @PathVariable String username) {
        ResponseEntity responseEntity = null;

        try {
            ResponseEntityHelper responesResponseEntityHelper = kelompokTktService.deleteKelompokTkt(id, username);
            if (responesResponseEntityHelper.getStatus() == 409) {
                responseEntity = new ResponseEntity<>(responesResponseEntityHelper.getMessage(), HttpStatus.CONFLICT);
            } else
                responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
