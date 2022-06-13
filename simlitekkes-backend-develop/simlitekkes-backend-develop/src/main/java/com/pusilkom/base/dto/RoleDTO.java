package com.pusilkom.base.dto;

import com.pusilkom.base.model.Role;

/**
 * Created by muhammad.khadafi on 11/03/2020.
 */
public class RoleDTO extends Role {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + getId() +
                ", roleCode='" + getRoleCode() + '\'' +
                ", roleName='" + getRoleName() + '\'' +
                ", username='" + getUsername() + '\'' +
                '}';
    }
}
