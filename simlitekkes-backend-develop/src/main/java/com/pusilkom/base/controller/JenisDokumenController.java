package com.pusilkom.base.controller;

import com.pusilkom.base.model.JenisDokumen;
import com.pusilkom.base.model.Role;
import com.pusilkom.base.service.JenisDokumenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by muhammad.khadafi on 2020-03-11 08:50:51.
 */

@RestController
public class JenisDokumenController {

    @Autowired
    JenisDokumenService jenisDokumenService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/jenis-dokumen-organisasi", method = RequestMethod.GET)
    public ResponseEntity getRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<JenisDokumen> jenisDokumenList = jenisDokumenService.getJenisDokumenOrg();
            responseEntity = ResponseEntity.ok(jenisDokumenList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

}
