package com.pusilkom.base.dto;

import com.pusilkom.base.model.Output;

/**
 * Created by ars on 04/03/2020.
 */
public class OutputDTO extends Output {
    String username;

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
                ", idJenisOutput=" + getIdJenisOutput() +
                ", idPenelitian=" + getIdPenelitian() +
                ", judul='" + getJudul() + '\'' +
                ", pathFile='" + getPathFile() + '\'' +
                ", timestampUpload=" + getTimestampUpload() +
                ", mediaPublikasi='" + getMediaPublikasi() + '\'' +
                ", tanggalPublikasi=" + getTanggalPublikasi() +
                ", username='" + username + '\'' +
                '}';
    }
}
