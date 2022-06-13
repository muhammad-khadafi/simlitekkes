package com.pusilkom.base.dto;

import com.pusilkom.base.model.KontribusiMitra;

/**
 * Created by ars on 05/05/2020.
 */
public class KontribusiMitraDTO extends KontribusiMitra {
    String username;
    Integer idPenelitian;
    Boolean isnew;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIdPenelitian() {
        return idPenelitian;
    }

    public void setIdPenelitian(Integer idPenelitian) {
        this.idPenelitian = idPenelitian;
    }

    public Boolean getIsnew() {
        return isnew;
    }

    public void setIsnew(Boolean isnew) {
        this.isnew = isnew;
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", id=" + getId() +
                ", idMitraPenelitian=" + getIdMitraPenelitian() +
                ", tahun=" + getTahun() +
                ", jumlahKontribusi=" + getJumlahKontribusi() +
                ", barangKontribusi='" + getBarangKontribusi() + '\'' +
                '}';
    }
}
