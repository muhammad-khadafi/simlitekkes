package com.pusilkom.base.controller;

import com.pusilkom.base.dto.BukuDTO;
import com.pusilkom.base.dto.WorkflowPenelitianDTO;
import com.pusilkom.base.model.JenisOutput;
import com.pusilkom.base.service.BukuService;
import com.pusilkom.base.service.OutputService;
import com.pusilkom.base.service.WorkflowPenelitianService;
import com.pusilkom.base.util.DebugUtil;
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
 * Created by code-generator on 2020-04-16 09:47:00.
 */

@RestController
public class WorkflowPenelitianController {
    @Autowired
    WorkflowPenelitianService workflowPenelitianService;
    @Autowired
    DebugUtil d;

    Logger log = LoggerFactory.getLogger(this.getClass());

    // insert
    @RequestMapping(value = "/workflow-penelitian", method = RequestMethod.POST)
    public ResponseEntity postBuku(@RequestBody WorkflowPenelitianDTO workflowPenelitianDTO) {
        ResponseEntity responseEntity = null;

        try {
            workflowPenelitianService.save(workflowPenelitianDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/workflow-penelitian", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody WorkflowPenelitianDTO workflowPenelitianDTO) {
        ResponseEntity responseEntity = null;
        try {
            workflowPenelitianService.update(workflowPenelitianDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/workflow-penelitian/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getListWorkflowPenelitian(@PathVariable Integer idPenelitian){
        ResponseEntity responseEntity = null;
        try {
            List<WorkflowPenelitianDTO> workflowPenelitianDTOList = workflowPenelitianService.getListWorkflowPenelitian(idPenelitian);
            responseEntity = ResponseEntity.ok(workflowPenelitianDTOList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
