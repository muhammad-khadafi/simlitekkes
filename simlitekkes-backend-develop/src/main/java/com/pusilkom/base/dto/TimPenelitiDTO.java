/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.dto;

import com.pusilkom.base.model.TimPeneliti;

/**
 *
 * @author Helmi Fakhriandi
 */
public class TimPenelitiDTO extends TimPeneliti {
    private String namaKetua;
    private String institusi;
    private String username;
    private Integer idUser;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNamaKetua() {
        return namaKetua;
    }

    public void setNamaKetua(String namaKetua) {
        this.namaKetua = namaKetua;
    }

    public String getInstitusi() {
        return institusi;
    }

    public void setInstitusi(String institusi) {
        this.institusi = institusi;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", id=" + getId() +
                ", idPeneliti=" + getIdPeneliti() +
                ", idPenelitian=" + getIdPenelitian() +
                ", idPeranPeneliti=" + getIdPeranPeneliti() +
                ", idOrganisasi=" + getIdOrganisasi() +
                ", idBidangKeahlianSaatIni=" + getIdBidangKeahlianSaatIni() +
                ", tugasPenelitian='" + getTugasPenelitian() + '\'' +
                ", alokasiJamPerMinggu=" + getAlokasiJamPerMinggu() +
                '}';
    }
}
