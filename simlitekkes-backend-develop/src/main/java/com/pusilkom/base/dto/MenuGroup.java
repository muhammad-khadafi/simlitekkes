package com.pusilkom.base.dto;

import com.pusilkom.base.model.Menu;

import java.util.List;

/**
 * Created by muhammad.khadafi on 14/02/2020.
 */
public class MenuGroup {

    private Menu menuParent;

    private List<Menu> listOfSubMenu;

    public Menu getMenuParent() {
        return menuParent;
    }

    public void setMenuParent(Menu menuParent) {
        this.menuParent = menuParent;
    }

    public List<Menu> getListOfSubMenu() {
        return listOfSubMenu;
    }

    public void setListOfSubMenu(List<Menu> listOfSubMenu) {
        this.listOfSubMenu = listOfSubMenu;
    }
}
