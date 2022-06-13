package com.pusilkom.base.service;

import com.pusilkom.base.dto.RoleMenuDTO;
import com.pusilkom.base.model.RoleHasMenu;
import com.pusilkom.base.model.RoleHasMenuExample;
import com.pusilkom.base.model.mapper.RoleHasMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhammad.khadafi on 19/02/2020.
 */

@Service
@Transactional
public class RoleMenuService {

    @Autowired
    RoleHasMenuMapper roleHasMenuMapper;

    @Autowired
    LogAktivitasService logAktivitasService;

    public List<RoleMenuDTO> getRoleMenu() {

        List<RoleMenuDTO> roleMenuDTOList = new ArrayList<>();
        roleMenuDTOList = roleHasMenuMapper.getRoleHasMenu();

        for (RoleMenuDTO roleMenuDTO :
                roleMenuDTOList) {
            String menusStr = "";
            List<Long> idMenus = new ArrayList<Long>();
            for (int i = 0; i < roleMenuDTO.getMenus().size(); i++) {
                menusStr = menusStr + (i == 0 ? "" : ", ") + roleMenuDTO.getMenus().get(i).getMenuName();
                idMenus.add(roleMenuDTO.getMenus().get(i).getId());
            }
            roleMenuDTO.setMenusStr(menusStr);
            roleMenuDTO.setIdMenus(idMenus);
        }

        return roleMenuDTOList;
    }

    public void saveRoleMenu(RoleMenuDTO roleMenuDTO) {

        logAktivitasService.addLog(roleMenuDTO.getUsername(), "POST", roleMenuDTO.toString(), "/role-menu");

        RoleHasMenu roleHasMenu = new RoleHasMenu();
        roleHasMenu.setIdRole(roleMenuDTO.getIdRole());

        for (Long idMenu : roleMenuDTO.getIdMenus()) {
            roleHasMenu.setIdMenu(idMenu);
            roleHasMenuMapper.insert(roleHasMenu);
        }

    }

    public void deleteRoleMenu(Long idRole, String username) {
        logAktivitasService.addLog(username, "DELETE", idRole.toString(), "/role-menu");

        RoleHasMenuExample roleHasMenuExample = new RoleHasMenuExample();
        roleHasMenuExample.createCriteria().andIdRoleEqualTo(idRole);
        roleHasMenuMapper.deleteByExample(roleHasMenuExample);

    }

    public void updateRoleMenu(RoleMenuDTO roleMenuDTO) {
        logAktivitasService.addLog(roleMenuDTO.getUsername(), "PUT", roleMenuDTO.toString(), "/role-menu");

        deleteRoleMenu(roleMenuDTO.getIdRole(), roleMenuDTO.getUsername());
        saveRoleMenu(roleMenuDTO);
    }
}
