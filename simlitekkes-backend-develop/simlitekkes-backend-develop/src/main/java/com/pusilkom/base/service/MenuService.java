package com.pusilkom.base.service;

import com.pusilkom.base.dto.MenuDTO;
import com.pusilkom.base.model.Menu;
import com.pusilkom.base.model.MenuExample;
import com.pusilkom.base.model.RoleHasMenu;
import com.pusilkom.base.model.RoleHasMenuExample;
import com.pusilkom.base.model.mapper.MenuMapper;
import com.pusilkom.base.model.mapper.RoleHasMenuMapper;
import org.modelmapper.ModelMapper;
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
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    LogAktivitasService logAktivitasService;

    @Autowired
    RoleHasMenuMapper roleHasMenuMapper;

    public List<MenuDTO> getMenu() {
        return menuMapper.getMenuList();
    }

    public List<MenuDTO> getMenu2Level() {
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andIdParentIsNull();
        menuExample.setOrderByClause("menu_order");
        List<Menu> parentMenuList = menuMapper.selectByExample(menuExample);
        List<MenuDTO> resultList = new ArrayList<>();

        for (Menu parentMenu :
                parentMenuList) {
            MenuDTO parentMenuDTO = toMenuDTO(parentMenu);
            menuExample = new MenuExample();
            menuExample.createCriteria().andIdParentEqualTo(parentMenu.getId());
            menuExample.setOrderByClause("menu_order");
            List<Menu> childMenuList = menuMapper.selectByExample(menuExample);
            List<MenuDTO> childMenuDTOList = new ArrayList<>();
            for (Menu menu :
                    childMenuList) {
                MenuDTO childMenuDTO = toMenuDTO(menu);

                MenuExample menuExample2 = new MenuExample();
                menuExample2.createCriteria().andIdEqualTo(menu.getIdParent());
                String menuParentName = menuMapper.selectByExample(menuExample2).get(0).getMenuName();

                childMenuDTO.setMenuParentName(menuParentName);
                childMenuDTOList.add(childMenuDTO);
            }
            resultList.add(parentMenuDTO);
            resultList.addAll(childMenuDTOList);
        }

        return resultList;
    }

    private MenuDTO toMenuDTO(Menu menu) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(menu, MenuDTO.class);
    }


    public List<Menu> getMenuParent() {
        MenuExample menuExample = new MenuExample();
        menuExample.setOrderByClause("menu_name");
        return menuMapper.selectByExample(menuExample);
    }

    public void saveMenu(MenuDTO menuDTO) {
        logAktivitasService.addLog(menuDTO.getUsername(), "POST", menuDTO.toString(), "/menu");
        menuMapper.insert(toMenu(menuDTO));
    }

    public Menu toMenu(MenuDTO menuDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(menuDTO, Menu.class);
    }

    public String deleteMenu(Long id, String username) {
        // Status => DELETED, HAS_USER_ROLE, HAS_CHILD
        String status = "";
        boolean isDelete = true;
        //checking role menu
        RoleHasMenuExample roleHasMenuExample = new RoleHasMenuExample();
        roleHasMenuExample.createCriteria().andIdMenuEqualTo(id);
        List<RoleHasMenu> roleHasMenus = roleHasMenuMapper.selectByExample(roleHasMenuExample);
        if (!roleHasMenus.isEmpty()){
            isDelete = false;
            status = "HAS_USER_ROLE";
            return status;
        }

        //checking menu parent
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andIdParentEqualTo(id);
        List<Menu> menus = menuMapper.selectByExample(menuExample);
        if (!menus.isEmpty()){
            isDelete = false;
            status = "HAS_CHILD";
            return status;
        }

        if (isDelete){
            logAktivitasService.addLog(username, "DELETE", id.toString(), "/menu");
            menuMapper.deleteByPrimaryKey(id);
            status = "DELETED";
        }
        return status;
    }

    public void updateMenu(MenuDTO menuDTO) {
        logAktivitasService.addLog(menuDTO.getUsername(), "PUT", menuDTO.toString(), "/menu");
        menuMapper.updateByPrimaryKey(toMenu(menuDTO));
    }
}
