package com.pusilkom.base.controller;

import com.pusilkom.base.dto.RoleDTO;
import com.pusilkom.base.dto.UserRoleDTO;
import com.pusilkom.base.helper.ResponseEntityHelper;
import com.pusilkom.base.model.Role;
import com.pusilkom.base.model.Userweb;
import com.pusilkom.base.service.RoleService;
import com.pusilkom.base.service.UserRoleService;
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
 * Created by muhammad.khadafi
 */

@RestController
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/user-role-all/{organizationId}/{roleCode}", method = RequestMethod.GET)
    public ResponseEntity getUserRoleAll(@PathVariable Integer organizationId, @PathVariable String roleCode) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<UserRoleDTO> userRoleList = userRoleService.getUserRole(organizationId, roleCode);
            responseEntity = ResponseEntity.ok(userRoleList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/user-role", method = RequestMethod.GET)
    public ResponseEntity getUserRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<UserRoleDTO> userRoleList = userRoleService.getUserRole();
            responseEntity = ResponseEntity.ok(userRoleList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/user-role/{organizationId}", method = RequestMethod.GET)
    public ResponseEntity getUserRoleByOrganization(@PathVariable Integer organizationId) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<UserRoleDTO> userRoleList = userRoleService.getUserRoleByOrganization(organizationId);
            responseEntity = ResponseEntity.ok(userRoleList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/user-role", method = RequestMethod.POST)
    public ResponseEntity postUserRole(@RequestBody UserRoleDTO userRoleDTO) {
        ResponseEntity responseEntity = null;

        try {
            userRoleService.saveUserRole(userRoleDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/user-role/{idUserweb}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserwebHasRole(@PathVariable Long idUserweb, @PathVariable String username) {
        ResponseEntity responseEntity = null;

        try {
            ResponseEntityHelper responesResponseEntityHelper = userRoleService.deleteUserRoleWithRespEnt(idUserweb, username);

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

    @RequestMapping(value = "/user-role", method = RequestMethod.PUT)
    public ResponseEntity updateUserwebHasRole(@RequestBody UserRoleDTO userRoleDTO) {
        ResponseEntity responseEntity = null;

        try {
            userRoleService.updateUserRole(userRoleDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

}
