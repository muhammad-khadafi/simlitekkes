package com.pusilkom.base.dto;

import com.pusilkom.base.model.Menu;

/**
 * Created by muhammad.khadafi on 21/02/2020.
 */
public class MenuDTO extends Menu {

    private String menuParentName;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMenuParentName() {
        return menuParentName;
    }

    public void setMenuParentName(String menuParentName) {
        this.menuParentName = menuParentName;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + getId() +
                ", idParent=" + getIdParent() +
                ", menuCode='" + getMenuCode() + '\'' +
                ", menuName='" + getMenuName() + '\'' +
                ", menuOrder=" + getMenuOrder() +
                ", isDisplayed=" + getIsDisplayed() +
                ", icon='" + getIcon() + '\'' +
                ", routerTo='" + getRouterTo() + '\'' +
                ", menuParentName='" + menuParentName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
