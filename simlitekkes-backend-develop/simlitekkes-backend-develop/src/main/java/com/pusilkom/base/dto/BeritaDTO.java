package com.pusilkom.base.dto;

import com.pusilkom.base.model.Berita;

/**
 * Created by ars on 04/03/2020.
 */
public class BeritaDTO extends Berita {
    private String isPublishedStr;
    private String namaUser;
    private String namaOrganisasi;
    private String publishTimeStr;

    public String getIsPublishedStr() {
        return isPublishedStr;
    }

    public void setIsPublishedStr(String isPublishedStr) {
        this.isPublishedStr = isPublishedStr;
    }
    
    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getNamaOrganisasi() {
        return namaOrganisasi;
    }

    public void setNamaOrganisasi(String namaOrganisasi) {
        this.namaOrganisasi = namaOrganisasi;
    }

    public String getPublishTimeStr() {
        return publishTimeStr;
    }

    public void setPublishTimeStr(String publishTimeStr) {
        this.publishTimeStr = publishTimeStr;
    }

    @Override
    public String toString() {
        return "" +
                "id=" + getId() +
                ", idOrganisasi=" + getIdOrganisasi() +
                ", judul='" + getJudul() + '\'' +
                ", isi='" + getIsi() + '\'' +
                ", isPublished=" + getIsPublished() +
                ", userPublish='" + getUserPublish() + '\'' +
                ", publishTimestamp=" + getPublishTimestamp() +
                '}';
    }
}