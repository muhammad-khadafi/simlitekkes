package com.pusilkom.base.controller;

import com.pusilkom.base.dto.SkemaPenelitianDTO;
import com.pusilkom.base.dto.TemaDTO;
import com.pusilkom.base.dto.TopikDTO;
import com.pusilkom.base.service.TemaService;
import com.pusilkom.base.service.TopikService;
import com.pusilkom.base.util.DebugUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by code-generator on 2020-03-11 09:47:00.
 */

@RestController
public class TopikController {

    @Autowired
    TopikService topikService;

    @Autowired
    DebugUtil d;

    Logger log = LoggerFactory.getLogger(this.getClass());

    // get all topik by id tema
    @RequestMapping(value = "/topik/{id}", method = RequestMethod.GET)
    public ResponseEntity getListTopik(@PathVariable("id") Integer id) {
        ResponseEntity responseEntity = null;
        TopikDTO searchForm = new TopikDTO();
        searchForm.setIdTema(id);
        try {
            log.info("searchform : {}", d.toString(searchForm));
            List<TopikDTO> topikDTOList = topikService.getListTopikDTO(searchForm);
            responseEntity = ResponseEntity.ok(topikDTOList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    // insert
    @RequestMapping(value = "/topik", method = RequestMethod.POST)
    public ResponseEntity postTopik(@RequestBody TopikDTO topikDTO) {
        ResponseEntity responseEntity = null;

        try {
            if(topikService.checkDuplicateNama(topikDTO)){
                responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Found");
            } else {
                topikService.save(topikDTO);
                responseEntity = ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/topik", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody TopikDTO topikDTO) {
        ResponseEntity responseEntity = null;
        try {
            if(topikService.checkDuplicateNama(topikDTO)){
                responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Found");
            } else {
                topikService.update(topikDTO);
                responseEntity = ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/topik/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id, @PathVariable String username) {
        ResponseEntity responseEntity = null;
        try {
            topikService.delete(id, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
