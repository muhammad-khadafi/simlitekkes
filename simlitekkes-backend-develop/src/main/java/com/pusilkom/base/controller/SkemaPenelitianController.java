package com.pusilkom.base.controller;

import com.pusilkom.base.dto.DownloadResponse;
import com.pusilkom.base.dto.SkemaPenelitianDTO;
import com.pusilkom.base.dto.MenuGroup;
import com.pusilkom.base.dto.Response;
import com.pusilkom.base.model.KategoriSkemaPenelitian;
import com.pusilkom.base.model.Organisasi;
import com.pusilkom.base.model.SkemaPenelitian;
import com.pusilkom.base.service.OrganisasiService;
import com.pusilkom.base.service.SampleService;
import com.pusilkom.base.service.SkemaPenelitianService;
import com.pusilkom.base.util.DownloadUtil;
import com.pusilkom.base.util.UploadUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by code-generator on 2020-03-11 09:47:00.
 */

@RestController
public class SkemaPenelitianController {

    @Autowired
    SkemaPenelitianService skemaPenelitianService;
    @Autowired
    OrganisasiService organisasiService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    // get all
    @RequestMapping(value = "/skemaPenelitian", method = RequestMethod.GET)
    public ResponseEntity getSkemaPenelitian() {
        ResponseEntity responseEntity = null;
        SkemaPenelitianDTO search = new SkemaPenelitianDTO();
        try {
            List<SkemaPenelitianDTO> skemaPenelitianList = skemaPenelitianService.getListSkemaPenelitian(search);
            responseEntity = ResponseEntity.ok(skemaPenelitianList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // get by id
    @RequestMapping(value = "/skemaPenelitian/{id}", method = RequestMethod.GET)
    public ResponseEntity getSkemaPenelitian(@PathVariable("id") Long id) {
        ResponseEntity responseEntity = null;
        SkemaPenelitianDTO searchForm = new SkemaPenelitianDTO();
        searchForm.setId(id.intValue());
        try {
            List<SkemaPenelitianDTO> skemaPenelitian = skemaPenelitianService.getListSkemaPenelitian(searchForm);
            if(skemaPenelitian != null && skemaPenelitian.size() > 0) {
                searchForm = skemaPenelitian.get(0);
            }
            responseEntity = ResponseEntity.ok(searchForm);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // insert
    @RequestMapping(value = "/skemaPenelitian", method = RequestMethod.POST)
    public ResponseEntity postSkemaPenelitian(@RequestBody SkemaPenelitianDTO skemaPenelitianDTO) {
        ResponseEntity responseEntity = null;

        try {
            skemaPenelitianService.save(skemaPenelitianDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/skemaPenelitian", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody SkemaPenelitianDTO skemaPenelitianDTO) {
        ResponseEntity responseEntity = null;

        try {
            skemaPenelitianService.update(skemaPenelitianDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/skemaPenelitian/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) {
        ResponseEntity responseEntity = null;

        try {
            skemaPenelitianService.delete(id);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/skemaPenelitian/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity getRekap(@PathVariable Long id) throws Exception{
        ResponseEntity responseEntity = null;
        try {
            SkemaPenelitian response = skemaPenelitianService.findById(id);
            responseEntity = ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    //get list kategori_skema_penelitian by klasifikasi
    @RequestMapping(value = "/skemaPenelitian/kategori/{klasifikasi}", method = RequestMethod.GET)
    public ResponseEntity getListKategoriSkemaPenelitianByKlasifikasi(@PathVariable Integer klasifikasi) throws Exception{
        ResponseEntity responseEntity = null;
        try {
            SkemaPenelitianDTO skemaPenelitianDTO = new SkemaPenelitianDTO();
            List<KategoriSkemaPenelitian> listKategori = new ArrayList<>();
            if(klasifikasi != null){
                if (klasifikasi == 1) {
                    skemaPenelitianDTO.setKlasifikasi1(true);
                } else if (klasifikasi == 2) {
                    skemaPenelitianDTO.setKlasifikasi2(true);
                } else if (klasifikasi == 3) {
                    skemaPenelitianDTO.setKlasifikasi3(true);
                }
                listKategori = skemaPenelitianService.getListKategoriByKlasifikasi(skemaPenelitianDTO);
            }
            responseEntity = ResponseEntity.ok(listKategori);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    // get list skema by idKategoriSkemaPenelitian & klasifikasi
    @RequestMapping(value = "/skemaPenelitian/{idKategori}/{klasifikasi}", method = RequestMethod.GET)
    public ResponseEntity getListSkemaPenelitianByIdKategoriAndKlasifikasi(@PathVariable("idKategori") Integer idKategori, @PathVariable("klasifikasi") Integer klasifikasi) {
        ResponseEntity responseEntity = null;
        try {
            List<SkemaPenelitian> skemaPenelitian = skemaPenelitianService.getListSkemaPenelitianByIdKategoriAndKlasifikasi(idKategori, klasifikasi);
            responseEntity = ResponseEntity.ok(skemaPenelitian);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    // get skema by idPenelitian
    @RequestMapping(value = "/skemaPenelitian/penelitian/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getListSkemaPenelitianByIdPenelitian(@PathVariable("idPenelitian") Integer idPenelitian) {
        ResponseEntity responseEntity = null;
        try {
            SkemaPenelitian skemaPenelitian = skemaPenelitianService.getListSkemaPenelitianByIdPenelitian(idPenelitian);
            responseEntity = ResponseEntity.ok(skemaPenelitian);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }
}
