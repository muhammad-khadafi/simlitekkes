package com.pusilkom.base.dto;


import com.pusilkom.base.model.KriteriaPenilaianPenelitian;

import java.util.Date;

/**
 * Created by ars
 */
public class DaftarReviewDTO {
    private Integer id;
    private String judul;
    private String status;
    private Date tanggalTerima;
    private Date tanggalSelesai;
    private String institusi;
    private Integer tahunUsulan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTanggalTerima() {
        return tanggalTerima;
    }

    public void setTanggalTerima(Date tanggalTerima) {
        this.tanggalTerima = tanggalTerima;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getInstitusi() {
        return institusi;
    }

    public void setInstitusi(String institusi) {
        this.institusi = institusi;
    }

    public Integer getTahunUsulan() {
        return tahunUsulan;
    }

    public void setTahunUsulan(Integer tahunUsulan) {
        this.tahunUsulan = tahunUsulan;
    }
}