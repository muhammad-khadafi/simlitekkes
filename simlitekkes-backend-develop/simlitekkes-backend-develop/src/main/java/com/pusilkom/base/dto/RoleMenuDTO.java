package com.pusilkom.base.dto;

import com.pusilkom.base.model.Menu;
import com.pusilkom.base.model.RoleHasMenu;

import java.util.List;

/**
 * Created by muhammad.khadafi on 17/03/2020.
 */
public class RoleMenuDTO extends RoleHasMenu {

    private String roleName;

    private String menusStr;

    private List<Menu> menus;

    private List<Long> idMenus;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMenusStr() {
        return menusStr;
    }

    public void setMenusStr(String menusStr) {
        this.menusStr = menusStr;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Long> getIdMenus() {
        return idMenus;
    }

    public void setIdMenus(List<Long> idMenus) {
        this.idMenus = idMenus;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + getId() +
                ", idRole=" + getIdRole() +
                ", idMenu=" + getIdMenu() +
                ", roleName='" + roleName + '\'' +
                ", menusStr='" + menusStr + '\'' +
                ", menus=" + menus +
                ", idMenus=" + idMenus +
                '}';
    }

}
