package com.pusilkom.base.controller;

import com.pusilkom.base.dto.MenuDTO;
import com.pusilkom.base.dto.UserwebDTO;
import com.pusilkom.base.model.Menu;
import com.pusilkom.base.model.Userweb;
import com.pusilkom.base.service.JwtUserDetailsService;
import com.pusilkom.base.service.MenuService;
import com.pusilkom.base.service.UserWebService;
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
 * Created by muhammad.khadafi on 19/02/2020.
 */

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ResponseEntity getMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<MenuDTO> menuList = menuService.getMenu();
            responseEntity = ResponseEntity.ok(menuList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/menuParent", method = RequestMethod.GET)
    public ResponseEntity getMenuParent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<Menu> menuList = menuService.getMenuParent();
            responseEntity = ResponseEntity.ok(menuList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    public ResponseEntity postMenu(@RequestBody MenuDTO menuDTO) {
        ResponseEntity responseEntity = null;

        try {
            menuService.saveMenu(menuDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/menu/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMenu(@PathVariable Long id, @PathVariable String username) {
        ResponseEntity responseEntity = null;

        try {
            String deleting = menuService.deleteMenu(id, username);
            responseEntity = ResponseEntity.ok(deleting);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/menu", method = RequestMethod.PUT)
    public ResponseEntity updateMenu(@RequestBody MenuDTO menuDTO) {
        ResponseEntity responseEntity = null;

        try {
            menuService.updateMenu(menuDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/menus-reset/{roleId}", method = RequestMethod.GET)
    public ResponseEntity resetMenus(@PathVariable Long roleId) throws Exception {
        ResponseEntity responseEntity = null;
        try {
            List<Menu> menus = jwtUserDetailsService.getMenus(roleId);
            responseEntity = ResponseEntity.ok(menus);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }


}
