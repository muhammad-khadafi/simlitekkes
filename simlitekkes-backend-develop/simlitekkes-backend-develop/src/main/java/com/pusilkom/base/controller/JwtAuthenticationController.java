package com.pusilkom.base.controller;

import com.pusilkom.base.config.JwtTokenUtil;
import com.pusilkom.base.dto.Response;
import com.pusilkom.base.model.*;
import com.pusilkom.base.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin

/**
 * Created by muhammad.khadafi on 01/11/2019.
 */
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        List<Role> roles = userDetailsService.getRolesByUsername(userDetails.getUsername());
        Userweb userweb = userDetailsService.getUserweb(userDetails.getUsername());
        List<Menu> menus = userDetailsService.getMenus(roles.get(0).getId());
        Organisasi organisasi = userDetailsService.getOrganisasi(userweb.getIdOrganisasi());

        Response response = new Response();
        response.setName(userDetails.getUsername());
        response.setUsername(userDetails.getUsername());
        response.setToken(token);
        response.setRoles(roles);
        response.setRole(roles.get(0));
        response.setUserId(userweb.getId());
        response.setOrganizationId(userweb.getIdOrganisasi());
        response.setMenus(menus);
        response.setOrganisasi(organisasi);

        return ResponseEntity.ok(response);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
