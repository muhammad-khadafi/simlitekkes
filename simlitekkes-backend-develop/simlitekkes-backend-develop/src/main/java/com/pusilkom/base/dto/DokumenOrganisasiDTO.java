package com.pusilkom.base.dto;

import com.pusilkom.base.model.DokumenOrganisasi;

/**
 * Created by muhammad.khadafi on 30/03/2020.
 */
public class DokumenOrganisasiDTO extends DokumenOrganisasi{

    private String namaJenisDokumen;

    private String username;

    private String namaOrganisasi;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNamaJenisDokumen() {
        return namaJenisDokumen;
    }

    public void setNamaJenisDokumen(String namaJenisDokumen) {
        this.namaJenisDokumen = namaJenisDokumen;
    }

    public String getNamaOrganisasi() {
        return namaOrganisasi;
    }

    public void setNamaOrganisasi(String namaOrganisasi) {
        this.namaOrganisasi = namaOrganisasi;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + getId() +
                ", idOrganisasi=" + getIdOrganisasi() +
                ", namaFile='" + getNamaFile() + '\'' +
                ", filePath='" + getFilePath() + '\'' +
                ", timestampUpload=" + getTimestampUpload() +
                ", userUpload='" + getUserUpload() + '\'' +
                ", judul=" + getJudul() +
                ", idJenisDokumen=" + getIdJenisDokumen() +
                ", nomorDokumen='" + getNomorDokumen() + '\'' +
                ", tahunTerbit='" + getTahunTerbit() + '\'' +
                ", namaJenisDokumen='" + namaJenisDokumen + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
