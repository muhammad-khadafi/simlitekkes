package com.pusilkom.base.dto;

import com.pusilkom.base.model.KelompokTkt;

public class KelompokTktDTO extends KelompokTkt {

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
                "username='" + username + '\'' +
                ", id=" + getId() +
                ", nama='" + getNama() + '\'' +
                ", isActive=" + getIsActive() +
                ", tahun='" + getTahun() + '\'' +
                "} ";
    }
}
