/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.dto;

import com.pusilkom.base.model.TargetOutputPenelitian;

/**
 *
 * @author Helmi Fakhriandi
 */
public class TargetOutputPenelitianDTO extends TargetOutputPenelitian {

    private String nama;
    private String username;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "TargetOutputPenelitianDTO{"
                + "id=" + getId()
                + ", idPenelitian=" + getIdPenelitian()
                + ", idJenisOutput=" + getIdJenisOutput()
                + ", tahunTarget='" + getTahunTarget() + '\''
                + ", tahunRealisasi='" + getTahunRealisasi() + '\''
                + ", isWajib=" + getIsWajib()
                + ", isTambahan=" + getIsTambahan()
                + ", nama='" + nama + "'}";
    }
}
