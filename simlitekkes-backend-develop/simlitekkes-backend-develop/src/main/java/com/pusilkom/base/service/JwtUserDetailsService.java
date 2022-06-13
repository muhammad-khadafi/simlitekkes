package com.pusilkom.base.service;

import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.MenuMapper;
import com.pusilkom.base.model.mapper.OrganisasiMapper;
import com.pusilkom.base.model.mapper.UserwebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhammad.khadafi on 01/11/2019.
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserwebMapper userwebMapper;
    @Autowired
    MenuMapper menuMapper;

    @Autowired
    OrganisasiMapper organisasiMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserwebExample userwebExample = new UserwebExample();
        userwebExample.createCriteria().andUsernameEqualTo(username);

        List<Userweb> userweb = userwebMapper.selectByExample(userwebExample);

        if (userweb != null){
            return new User(userweb.get(0).getUsername(), userweb.get(0).getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

    }

    public Role getRoleByUsername(String username){
        return userwebMapper.getRoleByUsername(username);
    }

    public List<Role> getRolesByUsername(String username){
        return userwebMapper.getRolesByUsername(username);
    }

    public Userweb getUserweb(String username) {
        UserwebExample userwebExample = new UserwebExample();
        userwebExample.createCriteria().andUsernameEqualTo(username);

        List<Userweb> userweb = userwebMapper.selectByExample(userwebExample);
        return userweb.get(0);
    }

    public List<Menu> getMenus(Long roleId) {
        List<Menu> result = new ArrayList<>();
        result = menuMapper.getMenusByRoleId(roleId);

        Menu menuByPass = new Menu();
        menuByPass.setMenuCode("HOME");

        Menu menuByPass2 = new Menu();
        menuByPass2.setMenuCode("LOGIN_HOME");

        Menu menuByPass3 = new Menu();
        menuByPass3.setMenuCode("404");

        result.add(menuByPass);
        result.add(menuByPass2);
        result.add(menuByPass3);

        return result;
    }

    public Organisasi getOrganisasi(Integer idOrganisasi) {
        return organisasiMapper.selectByPrimaryKey(idOrganisasi);
    }
}
