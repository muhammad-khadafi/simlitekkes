package com.pusilkom.base.controller;

import com.pusilkom.base.dto.DownloadResponse;
import com.pusilkom.base.dto.KontribusiMitraDTOGroupByMitraPenelitian;
import com.pusilkom.base.dto.MitraPenelitianDTO;
import com.pusilkom.base.dto.PenelitianDTO;
import com.pusilkom.base.helper.FileUploadHelper;
import com.pusilkom.base.model.*;
import com.pusilkom.base.service.MitraPenelitianService;
import com.pusilkom.base.service.OutputService;
import com.pusilkom.base.service.ValidasiService;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ars on 2020-05-05 09:47:00.
 */

@RestController
public class MitraPenelitianController {
    @Autowired
    MitraPenelitianService mitraPenelitianService;
    @Autowired
    OutputService outputService;
    @Autowired
    DebugUtil d;
    @Autowired
    DownloadUtil downloadUtil;
    @Autowired
    UploadUtil uploadUtil;
    @Autowired
    ValidasiService validasiService;
    @Value("${dir.upload.mou.mitra}") String DIR_UPLOAD_FILE;
    final static String TICKET_NOTVALID_MSG = "Ticket is not valid";

    Logger log = LoggerFactory.getLogger(this.getClass());

    // get mitraPenelitian by idpenelitian dan idjenismitra and execute validate based on role
    @RequestMapping(value = "/mitra-penelitian/{idPenelitian}/{idOrganisasi}/{role}/{userid}", method = RequestMethod.GET)
    public ResponseEntity getListMitraPenelitianByIdPenelitian(@PathVariable("idPenelitian") Integer idPenelitian,
                                                               @PathVariable("idOrganisasi") Integer idOrganisasi,
                                                               @PathVariable("role") String role,
                                                               @PathVariable("userid") Integer userid) {
        ResponseEntity responseEntity = null;
        try {
            List<MitraPenelitianDTO> dtoList = new ArrayList<>();
/*            Boolean validateResult = false;
            if (role.equalsIgnoreCase("kapus_ppm")) {
                validateResult = validasiService.getDataValidasiIsPenelitianSubmittedD14R(idPenelitian, idOrganisasi, Calendar.getInstance().get(Calendar.YEAR) + "");
            } else if (role.equalsIgnoreCase("dosen")) {
                validateResult = validasiService.getDataValidasiIsPenelitianNotSubmittedH14W(idPenelitian, userid);
            } else if (role.equalsIgnoreCase("pengelola_pusat")) {
                validateResult = validasiService.getDataValidasiIsPenelitianSubmittedF14R(idPenelitian, Calendar.getInstance().get(Calendar.YEAR) + "");
            }
            if (validateResult) {
                dtoList = mitraPenelitianService.getListMitraPenelitianDTO(idPenelitian);
            }*/
            dtoList = mitraPenelitianService.getListMitraPenelitianDTO(idPenelitian);
            responseEntity = ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    // insert
    @RequestMapping(value = "/mitra-penelitian", method = RequestMethod.POST)
    public ResponseEntity postMitraPenelitian(@RequestBody MitraPenelitianDTO mitraPenelitianDTO) {
        ResponseEntity responseEntity = null;

        try {
            Integer idMitra = mitraPenelitianService.save(mitraPenelitianDTO);
            responseEntity = ResponseEntity.ok(idMitra);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/mitra-penelitian", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody MitraPenelitianDTO mitraPenelitianDTO) {
        ResponseEntity responseEntity = null;
        try {
            mitraPenelitianService.update(mitraPenelitianDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/mitra-penelitian/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer id, @PathVariable String username) {
        ResponseEntity responseEntity = null;
        try {
            mitraPenelitianService.delete(id, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    // get detail mitraPenelitian by id
    @RequestMapping(value = "/mitra-penelitian/detail/{idMitra}", method = RequestMethod.GET)
    public ResponseEntity getDetailMitraPenelitianByIdOutput(@PathVariable Integer idMitra) {
        ResponseEntity responseEntity = null;
        try {
            MitraPenelitian mitra = mitraPenelitianService.getMitraPenelitianById(idMitra);
            responseEntity = ResponseEntity.ok(mitra);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    //get file mitraPenelitian
    @RequestMapping(value = "/mitra-penelitian/file/{id}", method = RequestMethod.GET)
    public ResponseEntity getMitraPenelitianFile(@PathVariable Integer id) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            String fileEncoded = mitraPenelitianService.getFile(id);
            responseEntity = ResponseEntity.ok(fileEncoded);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    //delete file mitraPenelitian
    @RequestMapping(value = "/mitra-penelitian/file/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMitraPenelitianFile(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;

        try {
            mitraPenelitianService.deleteFile(id);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    // get all jenis mitra
    @RequestMapping(value = "/jenis-mitra", method = RequestMethod.GET)
    public ResponseEntity getListJenisMitra() {
        ResponseEntity responseEntity = null;
        try {
            List<JenisMitra> dtoList = mitraPenelitianService.getAllJenisMitra();
            responseEntity = ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    //download file mitra
    @RequestMapping(value = "/mitra-penelitian/download/{id}", method = RequestMethod.POST)
    public ResponseEntity generateTicketDownload(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;

        try {
            String ticket = downloadUtil.generateTicket("/download/mitra-penelitian/" + id);
            responseEntity = ResponseEntity.ok(new DownloadResponse(ticket));
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    //download output
    @RequestMapping(value = "/download/mitra-penelitian/{id}", method = RequestMethod.GET)
    public void getDownloadDoc(@PathVariable Integer id, @RequestParam("ticket") String ticket, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            boolean isVerified = downloadUtil.verifyTicket(request, ticket);
            if (!isVerified) {
                response.getOutputStream().write(TICKET_NOTVALID_MSG.getBytes());
                return;
            }
            MitraPenelitian item = mitraPenelitianService.getMitraPenelitianById(id);
            InputStream is = new FileInputStream((item.getFilePath()));
            byte[] fileBytes = IOUtils.toByteArray(is);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + item.getNamaMitra() + ".pdf");
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

    //upload file mitra penelitian add
    @RequestMapping(value = "/mitra-penelitian/file", method = RequestMethod.POST)
    public ResponseEntity itemUploadPost(@RequestParam("file") MultipartFile fileUpload) {
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

    @RequestMapping(value = "/mitra-penelitian/rekap/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getRekapMitraPenelitianFromPenelitian(@PathVariable Integer idPenelitian) throws Exception {
        ResponseEntity responseEntity = null;
        try {
            KontribusiMitraDTOGroupByMitraPenelitian kmdtogbmp = mitraPenelitianService.getRekapMitraPenelitianFromPenelitian(idPenelitian);
            responseEntity = ResponseEntity.ok(kmdtogbmp);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    // get mitraPenelitian by idpenelitian
    @RequestMapping(value = "/mitra-penelitian/all/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getAllMitraPenelitianByIdPenelitian(@PathVariable("idPenelitian") Integer idPenelitian) {
        ResponseEntity responseEntity = null;
        try {
            List<MitraPenelitianDTO> dtoList = new ArrayList<>();
            dtoList = mitraPenelitianService.getListMitraPenelitianDTO(idPenelitian);
            responseEntity = ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }
}
