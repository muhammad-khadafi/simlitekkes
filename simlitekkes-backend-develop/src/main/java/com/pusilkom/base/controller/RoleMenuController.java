package com.pusilkom.base.controller;

import com.pusilkom.base.dto.RoleMenuDTO;
import com.pusilkom.base.dto.UserRoleDTO;
import com.pusilkom.base.service.RoleMenuService;
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
public class RoleMenuController {

    @Autowired
    RoleMenuService roleMenuService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/role-menu", method = RequestMethod.GET)
    public ResponseEntity getRoleMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<RoleMenuDTO> roleMenuList = roleMenuService.getRoleMenu();
            responseEntity = ResponseEntity.ok(roleMenuList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/role-menu", method = RequestMethod.POST)
    public ResponseEntity postRoleMenu(@RequestBody RoleMenuDTO roleMenuDTO) {
        ResponseEntity responseEntity = null;

        try {
            roleMenuService.saveRoleMenu(roleMenuDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/role-menu/{idRole}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteRoleMenu(@PathVariable Long idRole, @PathVariable String username) {
        ResponseEntity responseEntity = null;

        try {
            roleMenuService.deleteRoleMenu(idRole, username);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/role-menu", method = RequestMethod.PUT)
    public ResponseEntity updateRoleMenu(@RequestBody RoleMenuDTO roleMenuDTO) {
        ResponseEntity responseEntity = null;

        try {
            roleMenuService.updateRoleMenu(roleMenuDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

}
