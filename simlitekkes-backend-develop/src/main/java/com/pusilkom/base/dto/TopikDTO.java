package com.pusilkom.base.dto;

import com.pusilkom.base.model.Topik;

/**
 * Created by ars on 03/17/2020.
 */
public class TopikDTO extends Topik {
    String isActiveStr;
    String username;

    public String getIsActiveStr() {
        return isActiveStr;
    }

    public void setIsActiveStr(String isActiveStr) {
        this.isActiveStr = isActiveStr;
    }

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
                ", idTema=" + getIdTema() +
                ", nama='" + getNama() + '\'' +
                ", isActive=" + getIsActive() +
                ", username='" + username + '\'' +
                '}';
    }


}
