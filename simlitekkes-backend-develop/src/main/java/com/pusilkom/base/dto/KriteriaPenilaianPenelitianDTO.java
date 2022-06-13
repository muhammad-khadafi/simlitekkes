package com.pusilkom.base.dto;


import com.pusilkom.base.model.KriteriaPenilaianPenelitian;

/**
 * Created by ars
 */
public class KriteriaPenilaianPenelitianDTO extends KriteriaPenilaianPenelitian {
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
                ", idPenelitian=" + getIdPenelitian() +
                ", idKriteriaPenilaian=" + getIdKriteriaPenilaian() +
                ", idPilihanKriteriaPenilaian=" + getIdPilihanKriteriaPenilaian() +
                ", komentar='" + getKomentar() + '\'' +
                '}';
    }
}