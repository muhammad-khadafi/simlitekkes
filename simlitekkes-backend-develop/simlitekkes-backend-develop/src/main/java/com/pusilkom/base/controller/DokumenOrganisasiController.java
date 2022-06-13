package com.pusilkom.base.controller;

import com.pusilkom.base.dto.*;
import com.pusilkom.base.helper.FileUploadHelper;
import com.pusilkom.base.model.DokumenOrganisasi;
import com.pusilkom.base.service.DokumenOrganisasiService;
import com.pusilkom.base.service.UserRoleService;
import com.pusilkom.base.util.CommonUtil;
import com.pusilkom.base.util.DownloadUtil;
import com.pusilkom.base.util.UploadUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Date;
import java.util.List;

/**
 * Created by muhammad.khadafi
 */

@RestController
public class DokumenOrganisasiController {

    final static String TICKET_NOTVALID_MSG = "Ticket is not valid";
    final static String FILE_NOT_FOUND = "File not found";

    @Autowired
    DokumenOrganisasiService dokumenOrganisasiService;

    @Autowired
    UploadUtil uploadUtil;
    @Value("${dir.upload.dok.organisasi}") String DIR_UPLOAD_FILE;

    @Autowired
    DownloadUtil downloadUtil;

    @Autowired
    CommonUtil commonUtil;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/dokumen-organisasi/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity getListDokumenOrganisasi(@PathVariable Integer idOrganisasi) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<DokumenOrganisasiDTO> dokumenOrganisasiList = dokumenOrganisasiService.getListData(idOrganisasi);
            responseEntity = ResponseEntity.ok(dokumenOrganisasiList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/dokumen-organisasi/download/{id}", method = RequestMethod.POST)
    public ResponseEntity generateTicketDownload(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;

        try {
            String ticket = downloadUtil.generateTicket("/download/dokumen-organisasi/" + id);
            responseEntity = ResponseEntity.ok(new DownloadResponse(ticket));
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/dokumen-organisasi", method = RequestMethod.POST)
    public ResponseEntity postDokumenOrganisasi(@RequestBody DokumenOrganisasiDTO dokumenOrganisasiDTO) {
        ResponseEntity responseEntity = null;

        try {
            dokumenOrganisasiDTO.setUserUpload(dokumenOrganisasiDTO.getUsername());
            dokumenOrganisasiDTO.setTimestampUpload(new Date());
            dokumenOrganisasiService.saveDokumenOrganisasi(dokumenOrganisasiDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/dokumen-organisasi-upload/", method = RequestMethod.POST)
    public ResponseEntity dokumenOrganisasiUpload(@RequestParam("file") MultipartFile fileUpload) {
        ResponseEntity responseEntity = null;
        try {
            FileUploadHelper fileUploadHelper = uploadUtil.uploadFileCommonService(DIR_UPLOAD_FILE, fileUpload);
            responseEntity = ResponseEntity.ok().body(fileUploadHelper);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/dokumen-organisasi-upload/", method = RequestMethod.PUT)
    public ResponseEntity dokumenOrganisasiUploadUpdate(@RequestParam("file") MultipartFile fileUpload) {
        ResponseEntity responseEntity = null;
        try {
//            String nameWithoutTimeStamp = commonUtil.removeTimeStamp(fileUpload.getOriginalFilename());
            FileUploadHelper fileUploadHelper = uploadUtil.uploadFileCommonServiceUpdate(DIR_UPLOAD_FILE, fileUpload, fileUpload.getOriginalFilename());
            responseEntity = ResponseEntity.ok().body(fileUploadHelper);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/dokumen-organisasi-file/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteDokumenOrganisasiFile(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;

        try {
            dokumenOrganisasiService.deleteFile(id);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/dokumen-organisasi/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteDokumenOrganisasi(@PathVariable Integer id, @PathVariable String username) {
        ResponseEntity responseEntity = null;

        try {
            dokumenOrganisasiService.deleteDokumenOrganisasi(id, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/dokumen-organisasi-file/{id}", method = RequestMethod.GET)
    public ResponseEntity getDokumenOrganisasiFile(@PathVariable Integer id) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            String fileEncoded = dokumenOrganisasiService.getFile(id);
            responseEntity = ResponseEntity.ok(fileEncoded);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/download/dokumen-organisasi/{id}", method = RequestMethod.GET)
    public void getDownloadDoc(@PathVariable Integer id, @RequestParam("ticket") String ticket, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            boolean isVerified = downloadUtil.verifyTicket(request, ticket);

            if (!isVerified) {
                response.getOutputStream().write(TICKET_NOTVALID_MSG.getBytes());
                return;
            }

            DokumenOrganisasi dokumenOrganisasi = dokumenOrganisasiService.getDokumenOrganisasi(id);

            InputStream is = new FileInputStream(dokumenOrganisasi.getFilePath());

            byte[] fileBytes = IOUtils.toByteArray(is);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + dokumenOrganisasi.getNamaFile());

            response.getOutputStream().write(fileBytes);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            response.getOutputStream().write(FILE_NOT_FOUND.getBytes());
            return;
        }
    }

    @RequestMapping(value = "/dokumen-organisasi", method = RequestMethod.PUT)
    public ResponseEntity updateDokumenOrganisasi(@RequestBody DokumenOrganisasiDTO dokumenOrganisasiDTO) {
        ResponseEntity responseEntity = null;

        try {
            dokumenOrganisasiDTO.setUserUpload(dokumenOrganisasiDTO.getUsername());
            dokumenOrganisasiDTO.setTimestampUpload(new Date());
            dokumenOrganisasiService.updateDokumenOrganisasi(dokumenOrganisasiDTO);
            responseEntity = ResponseEntity.ok().body(dokumenOrganisasiDTO);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/dokumen-penugasan-reviewer/{idOrganisasi}/{tahunUsulan}/{kataKunci}", method = RequestMethod.GET)
    public ResponseEntity getDokumenPenugasanReviewer(@PathVariable("idOrganisasi") Integer idOrganisasi, @PathVariable("tahunUsulan") String tahunUsulan, @PathVariable("kataKunci") String kataKunci , HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity responseEntity = null;
        try {
            List<DokumenOrganisasiDTO> daftarDokumen = dokumenOrganisasiService.getDokumenPenugasanReviewer(idOrganisasi,tahunUsulan,kataKunci);
            responseEntity = ResponseEntity.ok(daftarDokumen);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
