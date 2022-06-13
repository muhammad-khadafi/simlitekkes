package com.pusilkom.base.dto;

import com.pusilkom.base.model.KontribusiMitra;
import com.pusilkom.base.model.MitraPenelitian;

import java.util.List;

/**
 * Created by ars on 05/05/2020.
 */
public class MitraPenelitianDTO extends MitraPenelitian {
    String username;
    Integer jumlahKontribusi;
    String jumlahKontribusiStr;
    String jenisMitraStr;
    String fileName;
    List<KontribusiMitra> listKontri;
    String rekapBarang;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getJumlahKontribusi() {
        return jumlahKontribusi;
    }

    public void setJumlahKontribusi(Integer jumlahKontribusi) {
        this.jumlahKontribusi = jumlahKontribusi;
    }

    public String getJumlahKontribusiStr() {
        return jumlahKontribusiStr;
    }

    public void setJumlahKontribusiStr(String jumlahKontribusiStr) {
        this.jumlahKontribusiStr = jumlahKontribusiStr;
    }

    public String getJenisMitraStr() {
        return jenisMitraStr;
    }

    public void setJenisMitraStr(String jenisMitraStr) {
        this.jenisMitraStr = jenisMitraStr;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<KontribusiMitra> getListKontri() {
        return listKontri;
    }

    public void setListKontri(List<KontribusiMitra> listKontri) {
        this.listKontri = listKontri;
    }

    public String getRekapBarang() {
        return rekapBarang;
    }

    public void setRekapBarang(String rekapBarang) {
        this.rekapBarang = rekapBarang;
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", id=" + getId() +
                ", idJenisMitra=" + getIdJenisMitra() +
                ", idPenelitian=" + getIdPenelitian() +
                ", namaMitra='" + getNamaMitra() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", penanggungJawab='" + getPenanggungJawab() + '\'' +
                ", filePath='" + getFilePath() + '\'' +
                ", timestampUpload=" + getTimestampUpload() +
                '}';
    }
}
