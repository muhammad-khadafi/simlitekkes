package com.pusilkom.base.controller;

import com.pusilkom.base.dto.DownloadResponse;
import com.pusilkom.base.dto.RoleDTO;
import com.pusilkom.base.dto.MenuGroup;
import com.pusilkom.base.dto.Response;
import com.pusilkom.base.helper.ResponseEntityHelper;
import com.pusilkom.base.model.Role;
import com.pusilkom.base.service.RoleService;
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
 * Created by muhammad.khadafi on 2020-03-11 08:50:51.
 */

@RestController
public class RoleController {

    @Autowired
    RoleService roleService;
    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public ResponseEntity getRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<Role> roleList = roleService.getRoleList();
            responseEntity = ResponseEntity.ok(roleList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public ResponseEntity getRole(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            Role role = roleService.getRoleById(id);
            responseEntity = ResponseEntity.ok(role);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public ResponseEntity postRole(@RequestBody RoleDTO roleDTO) {
        ResponseEntity responseEntity = null;
        try {
            roleService.saveRole(roleDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // update
    @RequestMapping(value = "/role", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody RoleDTO roleDTO) {
        ResponseEntity responseEntity = null;

        try {
            roleService.updateRole(roleDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    // delete
    @RequestMapping(value = "/role/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id, @PathVariable String username) {
        ResponseEntity responseEntity = null;

        try {
            ResponseEntityHelper responesResponseEntityHelper = roleService.deleteRole(id, username);
            if (responesResponseEntityHelper.getStatus() == 409) {
                responseEntity = new ResponseEntity<>(responesResponseEntityHelper.getMessage(), HttpStatus.CONFLICT);
            } else
                responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/role/roleCode/{roleCode}", method = RequestMethod.GET)
    public ResponseEntity getRoleByRoleCode(@PathVariable("roleCode") String roleCode) {
        ResponseEntity responseEntity = null;

        try {
            Role role = roleService.getRoleByRoleCode(roleCode);
            responseEntity = ResponseEntity.ok(role);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }


}
