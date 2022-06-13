package com.pusilkom.base.controller;

import com.pusilkom.base.dto.BukuDTO;
import com.pusilkom.base.model.JenisOutput;
import com.pusilkom.base.service.OutputService;
import com.pusilkom.base.service.BukuService;
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
public class BukuController {
    @Autowired
    BukuService bukuService;
    @Autowired
    OutputService outputService;
    @Autowired
    DebugUtil d;

    Logger log = LoggerFactory.getLogger(this.getClass());

    // get all buku
    @RequestMapping(value = "/buku/{idUser}", method = RequestMethod.GET)
    public ResponseEntity getListBuku(@PathVariable Integer idUser) {
        ResponseEntity responseEntity = null;
        BukuDTO search = new BukuDTO();
        search.setIdUser(idUser);
        try {
            List<BukuDTO> dtoList = bukuService.getListBukuDTO(search);
            responseEntity = ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // insert
    @RequestMapping(value = "/buku", method = RequestMethod.POST)
    public ResponseEntity postBuku(@RequestBody BukuDTO bukuDTO) {
        ResponseEntity responseEntity = null;

        try {
            bukuService.save(bukuDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/buku", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody BukuDTO bukuDTO) {
        ResponseEntity responseEntity = null;
        try {
            bukuService.update(bukuDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/buku/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer id, @PathVariable String username) {
        ResponseEntity responseEntity = null;
        try {
            bukuService.delete(id, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    // get detail buku by id
    @RequestMapping(value = "/buku/detail/{idOutput}", method = RequestMethod.GET)
    public ResponseEntity getDetailBukuByIdOutput(@PathVariable Integer idOutput) {
        ResponseEntity responseEntity = null;
        BukuDTO search = new BukuDTO();
        search.setIdOutput(idOutput);
        try {
            List<BukuDTO> dtoList = bukuService.getListBukuDTO(search);
            responseEntity = ResponseEntity.ok(dtoList.get(0));
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // get all jenis output buku
    @RequestMapping(value = "/jenis-output/buku", method = RequestMethod.GET)
    public ResponseEntity getAllJenisOutput() {
        ResponseEntity responseEntity = null;
        try {
            List<JenisOutput> jenisOutputs = outputService.getAllJenisOutput("buku");
            responseEntity = ResponseEntity.ok(jenisOutputs);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    //get file buku
    @RequestMapping(value = "/buku-file/{id}", method = RequestMethod.GET)
    public ResponseEntity getBukuFile(@PathVariable Integer id) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            String fileEncoded = outputService.getFile(id);
            responseEntity = ResponseEntity.ok(fileEncoded);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    //delete file buku
    @RequestMapping(value = "/buku-file/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBukuFile(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;

        try {
            outputService.deleteFile(id);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
