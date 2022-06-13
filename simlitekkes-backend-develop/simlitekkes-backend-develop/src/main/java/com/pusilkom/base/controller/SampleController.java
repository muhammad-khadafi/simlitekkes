package com.pusilkom.base.controller;

import com.pusilkom.base.dto.DownloadResponse;
import com.pusilkom.base.dto.EmployeeDTO;
import com.pusilkom.base.dto.MenuGroup;
import com.pusilkom.base.dto.Response;
import com.pusilkom.base.model.Employee;
import com.pusilkom.base.model.File;
import com.pusilkom.base.service.SampleService;
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
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by muhammad.khadafi on 23/09/2019.
 */

@RestController
public class SampleController {

    final static String TICKET_NOTVALID_MSG = "Ticket is not valid";
    @Autowired
    SampleService sampleService;
    @Autowired
    UploadUtil uploadUtil;
    @Autowired
    DownloadUtil downloadUtil;
    @Value("${dir.upload.file}")
    String DIR_UPLOAD_FILE;


    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity getSample() throws Exception {

        Response response1 = new Response();
        response1.setName(" Albert");
        return ResponseEntity.ok(response1);

    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity getEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<Employee> employeeList = sampleService.getEmployeeDate();
            responseEntity = ResponseEntity.ok(employeeList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity postEmployee(@RequestBody EmployeeDTO employeeDTO) {
        ResponseEntity responseEntity = null;

        try {
            sampleService.saveEmployee(employeeDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        ResponseEntity responseEntity = null;

        try {
            sampleService.deleteEmployee(id);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        ResponseEntity responseEntity = null;

        try {
            sampleService.updateEmployee(employeeDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public ResponseEntity getListFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<File> fileList = sampleService.getFileList();
            responseEntity = ResponseEntity.ok(fileList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public ResponseEntity fileUpload(@RequestParam("file") MultipartFile[] fileUpload) {
        ResponseEntity responseEntity = null;
        try {

            Map<String, String> response = null;

            if (fileUpload.length > 0) {
                response = uploadUtil.uploadFile(DIR_UPLOAD_FILE, fileUpload);
            }
            responseEntity = ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/file/download/{id}", method = RequestMethod.POST)
    public ResponseEntity getTicketDownload(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity responseEntity = null;

        try {
            String ticket = downloadUtil.generateTicket("/file/download/" + id);
            responseEntity = ResponseEntity.ok(new DownloadResponse(ticket));
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/file/download/{id}", method = RequestMethod.GET)
    public void getDownloadDoc(@PathVariable Long id, @RequestParam("ticket") String ticket, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            boolean isVerified = downloadUtil.verifyTicket(request, ticket);

            if (!isVerified) {
                response.getOutputStream().write(TICKET_NOTVALID_MSG.getBytes());
                return;
            }

            File fileDoc = sampleService.getFile(id);
            String ext1 = FilenameUtils.getExtension(DIR_UPLOAD_FILE + "/" + fileDoc.getFileName());
            String tipeKonten = "image/" + ext1;
            InputStream is = new FileInputStream(DIR_UPLOAD_FILE + "/" + fileDoc.getFileName());
            byte[] fileBytes = IOUtils.toByteArray(is);

            response.setContentType(tipeKonten);
            response.setHeader("Content-Disposition", "attachment;filename=" + fileDoc.getFileName());

            response.getOutputStream().write(fileBytes);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            response.getOutputStream().write(TICKET_NOTVALID_MSG.getBytes());
            return;
        }
    }

//    @RequestMapping(value = "/menu/{role}", method = RequestMethod.GET)
//    public ResponseEntity getListMenu(@PathVariable String role,HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ResponseEntity responseEntity = null;
//
//        try {
//            List<Menu> menuList = sampleService.getMenuList(role);
//            responseEntity = ResponseEntity.ok(menuList);
//        } catch (Exception e) {
//            log.error("An exception occurred!", e);
//        }
//
//        return responseEntity;
//    }

    @RequestMapping(value = "/menu/{role}", method = RequestMethod.GET)
    public ResponseEntity getListMenu(@PathVariable String role, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<MenuGroup> menuList = sampleService.getMenuList(role);
            responseEntity = ResponseEntity.ok(menuList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }
}
