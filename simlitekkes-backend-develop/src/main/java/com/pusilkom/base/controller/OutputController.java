package com.pusilkom.base.controller;

import com.pusilkom.base.dto.DownloadResponse;
import com.pusilkom.base.dto.JenisOutputDTO;
import com.pusilkom.base.dto.OutputDTO;
import com.pusilkom.base.helper.FileUploadHelper;
import com.pusilkom.base.model.JenisOutput;
import com.pusilkom.base.model.Output;
import com.pusilkom.base.service.OutputService;
import com.pusilkom.base.util.CommonUtil;
import com.pusilkom.base.util.DebugUtil;
import com.pusilkom.base.util.DownloadUtil;
import com.pusilkom.base.util.UploadUtil;
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
import java.util.List;

/**
 * Created by ars on 2020-04-03 09:47:00.
 */

@RestController
public class OutputController {

    final static String TICKET_NOTVALID_MSG = "Ticket is not valid";
    @Autowired
    DownloadUtil downloadUtil;
    @Autowired
    OutputService outputService;
    @Autowired
    UploadUtil uploadUtil;
    @Value("${dir.upload.jurnal}") String DIR_UPLOAD_FILE;
    @Value("${dir.upload.hki}")  String DIR_UPLOAD_FILE_HKI;
    @Value("${dir.upload.prosiding}")  String DIR_UPLOAD_FILE_PROSIDING;
    @Value("${dir.upload.buku}")  String DIR_UPLOAD_FILE_BUKU;
    @Autowired
    CommonUtil commonUtil;
    @Autowired
    DebugUtil d;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //upload file jurnal add
    @RequestMapping(value = "/output-upload/jurnal", method = RequestMethod.POST)
    public ResponseEntity jurnalUpload(@RequestParam("file") MultipartFile fileUpload) {
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

    //upload file hki add
    @RequestMapping(value = "/output-upload/hki", method = RequestMethod.POST)
    public ResponseEntity hkiUpload(@RequestParam("file") MultipartFile fileUpload) {
        ResponseEntity responseEntity = null;
        try {
            FileUploadHelper fileUploadHelper = uploadUtil.uploadFileCommonService(DIR_UPLOAD_FILE_HKI, fileUpload);
            responseEntity = ResponseEntity.ok().body(fileUploadHelper);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //upload file prosiding add
    @RequestMapping(value = "/output-upload/prosiding", method = RequestMethod.POST)
    public ResponseEntity prosidingUpload(@RequestParam("file") MultipartFile fileUpload) {
        ResponseEntity responseEntity = null;
        try {
            FileUploadHelper fileUploadHelper = uploadUtil.uploadFileCommonService(DIR_UPLOAD_FILE_PROSIDING, fileUpload);
            responseEntity = ResponseEntity.ok().body(fileUploadHelper);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //upload file buku add
    @RequestMapping(value = "/output-upload/buku", method = RequestMethod.POST)
    public ResponseEntity bukuUpload(@RequestParam("file") MultipartFile fileUpload) {
        ResponseEntity responseEntity = null;
        try {
            FileUploadHelper fileUploadHelper = uploadUtil.uploadFileCommonService(DIR_UPLOAD_FILE_BUKU, fileUpload);
            responseEntity = ResponseEntity.ok().body(fileUploadHelper);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //get output by id
    @RequestMapping(value = "/output-file/{id}", method = RequestMethod.GET)
    public ResponseEntity getOutputFile(@PathVariable Integer id) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            String fileEncoded = outputService.getFile(id);
            responseEntity = ResponseEntity.ok(fileEncoded);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    //delete output file by id
    @RequestMapping(value = "/output-file/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOutputFile(@PathVariable Integer id) {
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

    //download file output
    @RequestMapping(value = "/output/download/{id}", method = RequestMethod.POST)
    public ResponseEntity generateTicketDownload(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;

        try {
            String ticket = downloadUtil.generateTicket("/download/output/" + id);
            responseEntity = ResponseEntity.ok(new DownloadResponse(ticket));
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    //download output
    @RequestMapping(value = "/download/output/{id}", method = RequestMethod.GET)
    public void getDownloadDoc(@PathVariable Integer id, @RequestParam("ticket") String ticket, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            boolean isVerified = downloadUtil.verifyTicket(request, ticket);
            if (!isVerified) {
                response.getOutputStream().write(TICKET_NOTVALID_MSG.getBytes());
                return;
            }
            Output output = outputService.getOutputById(id);
            InputStream is = new FileInputStream(output.getPathFile());
            byte[] fileBytes = IOUtils.toByteArray(is);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + output.getJudul() + ".pdf");
            is.close();
            response.getOutputStream().write(fileBytes);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            log.info("error : {}", d.toString(e.toString()));
            response.getOutputStream().write(TICKET_NOTVALID_MSG.getBytes());
            return;
        }
    }

    // get all jenis output
    @RequestMapping(value = "/jenis-output/all", method = RequestMethod.GET)
    public ResponseEntity getAllJenisOutput() {
        ResponseEntity responseEntity = null;
        try {
            List<JenisOutput> jenisOutputs = outputService.getAllJenisOutput("");
            responseEntity = ResponseEntity.ok(jenisOutputs);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // insert
    @RequestMapping(value = "/output", method = RequestMethod.POST)
    public ResponseEntity postOutput(@RequestBody OutputDTO outputDTO) {
        ResponseEntity responseEntity = null;

        try {
            outputService.save(outputDTO);
            responseEntity = ResponseEntity.ok(outputDTO.getId());
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/output", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody OutputDTO outputDTO) {
        ResponseEntity responseEntity = null;
        try {
            outputService.update(outputDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    //delete
    @RequestMapping(value = "/output/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer id, @PathVariable String username) {
        ResponseEntity responseEntity = null;
        try {
            outputService.delete(id, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //get info jumlah output per jenis output
    @RequestMapping(value = "/jenis-output/info-jumlah/{idUser}", method = RequestMethod.GET)
    public ResponseEntity getInfoJumlahOutputPerJenisOutput(@PathVariable Integer idUser) throws Exception {
        ResponseEntity responseEntity = null;
        try {
            List<JenisOutputDTO> listJenisOutput = outputService.getInfoJumlahOutputPerJenisOutput(idUser);
            responseEntity = ResponseEntity.ok(listJenisOutput);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }
}
