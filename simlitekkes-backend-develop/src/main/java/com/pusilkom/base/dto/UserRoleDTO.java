package com.pusilkom.base.dto;

import com.pusilkom.base.model.Role;
import com.pusilkom.base.model.UserwebHasRole;

import java.util.List;

/**
 * Created by muhammad.khadafi on 12/03/2020.
 */
public class UserRoleDTO extends UserwebHasRole{

    private String username;

    private List<Role> roles;

    private String rolesStr;

    private List<Long> idRoles;

    public List<Long> getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(List<Long> idRoles) {
        this.idRoles = idRoles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getRolesStr() {
        return rolesStr;
    }

    public void setRolesStr(String rolesStr) {
        this.rolesStr = rolesStr;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + getId() +
                ", idUserweb=" + getIdUserweb() +
                ", idRole=" + getIdRole() +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                ", rolesStr='" + rolesStr + '\'' +
                ", idRoles=" + idRoles +
                '}';
    }
}
