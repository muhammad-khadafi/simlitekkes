/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.controller;

import com.pusilkom.base.model.RumpunIlmu;
import com.pusilkom.base.service.RumpunIlmuService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Helmi Fakhriandi
 */
@RestController
public class RumpunIlmuController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RumpunIlmuService rumpunIlmuService;

    @RequestMapping(value = "/rumpun-ilmu/{level}/{idParent}", method = RequestMethod.GET)
    public ResponseEntity getAllLevel1(@PathVariable Short level, @PathVariable Integer idParent) {
        ResponseEntity responseEntity = null;
        try {
            List<RumpunIlmu> listRumpunIlmu = rumpunIlmuService.getRumpunIlmubyLevel(level, idParent);
            responseEntity = ResponseEntity.ok(listRumpunIlmu);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }
}
