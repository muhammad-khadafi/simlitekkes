package com.pusilkom.base.dto;

import com.pusilkom.base.model.Penelitian;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Helmi Fakhriandi
 */
public class PenelitianDTO extends Penelitian{

    private String namaSkema;
    private Integer jumlahUsulan;
    private String tahunPelaksanaan;
    private String pengusul;
    private String status;
    private String tahap;
    private String namaPeran;
    private String username;
    private Integer idStatusTahapPenelitian;
    private Short tktSaatIni;
    private Short tktTarget;
    private Integer idTema;
    private Integer lamaKegiatan;
    private Integer idIlmuLv1;
    private Integer IdIlmuLv2;
    private Integer idIlmuLv3;
    private Date tanggalTerima;
    private Date tanggalDeadline;
    private String kodeStatus;

    public Integer getIdIlmuLv1() {
        return idIlmuLv1;
    }

    public void setIdIlmuLv1(Integer idIlmuLv1) {
        this.idIlmuLv1 = idIlmuLv1;
    }

    public Integer getIdIlmuLv2() {
        return IdIlmuLv2;
    }

    public void setIdIlmuLv2(Integer idIlmuLv2) {
        IdIlmuLv2 = idIlmuLv2;
    }

    public Integer getIdIlmuLv3() {
        return idIlmuLv3;
    }

    public void setIdIlmuLv3(Integer idIlmuLv3) {
        this.idIlmuLv3 = idIlmuLv3;
    }

    public Integer getIdTema() {
        return idTema;
    }

    public void setIdTema(Integer idTema) {
        this.idTema = idTema;
    }

    public Integer getLamaKegiatan() {
        return lamaKegiatan;
    }

    public void setLamaKegiatan(Integer lamaKegiatan) {
        this.lamaKegiatan = lamaKegiatan;
    }

    public Short getTktSaatIni() {
        return tktSaatIni;
    }

    public void setTktSaatIni(Short tktSaatIni) {
        this.tktSaatIni = tktSaatIni;
    }

    public Short getTktTarget() {
        return tktTarget;
    }

    public void setTktTarget(Short tktTarget) {
        this.tktTarget = tktTarget;
    }

    public String getNamaSkema() {
        return namaSkema;
    }

    public void setNamaSkema(String namaSkema) {
        this.namaSkema = namaSkema;
    }

    public Integer getJumlahUsulan() {
        return jumlahUsulan;
    }

    public void setJumlahUsulan(Integer jumlahUsulan) {
        this.jumlahUsulan = jumlahUsulan;
    }

    public String getTahunPelaksanaan() {
        return tahunPelaksanaan;
    }

    public void setTahunPelaksanaan(String tahunPelaksanaan) {
        this.tahunPelaksanaan = tahunPelaksanaan;
    }

    public String getPengusul() {
        return pengusul;
    }

    public void setPengusul(String pengusul) {
        this.pengusul = pengusul;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTahap() {
        return tahap;
    }

    public void setTahap(String tahap) {
        this.tahap = tahap;
    }

    public String getNamaPeran() {
        return namaPeran;
    }

    public void setNamaPeran(String namaPeran) {
        this.namaPeran = namaPeran;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIdStatusTahapPenelitian() {
        return idStatusTahapPenelitian;
    }

    public void setIdStatusTahapPenelitian(Integer idStatusTahapPenelitian) {
        this.idStatusTahapPenelitian = idStatusTahapPenelitian;
    }

    public Date getTanggalTerima() {
        return tanggalTerima;
    }

    public void setTanggalTerima(Date tanggalTerima) {
        this.tanggalTerima = tanggalTerima;
    }

    public Date getTanggalDeadline() {
        return tanggalDeadline;
    }

    public void setTanggalDeadline(Date tanggalDeadline) {
        this.tanggalDeadline = tanggalDeadline;
    }

    public String getKodeStatus() {
        return kodeStatus;
    }

    public void setKodeStatus(String kodeStatus) {
        this.kodeStatus = kodeStatus;
    }
    
    @Override
    public String toString() {
        return "{" +
                "namaSkema='" + namaSkema + '\'' +
                ", jumlahUsulan=" + jumlahUsulan +
                ", tahunPelaksanaan='" + tahunPelaksanaan + '\'' +
                ", pengusul='" + pengusul + '\'' +
                ", status='" + status + '\'' +
                ", tahap='" + tahap + '\'' +
                ", namaPeran='" + namaPeran + '\'' +
                ", id=" + getId() +
                ", idTopik=" + getIdTopik() +
                ", idSkemaPenelitian=" + getIdSkemaPenelitian() +
                ", idRumpunIlmu=" + getIdRumpunIlmu() +
                ", idTingkatKesiapanTeknologi=" + getIdTingkatKesiapanTeknologi() +
                ", idTingkatKesiapanTeknologiTarget=" + getIdTingkatKesiapanTeknologiTarget() +
                ", judul='" + getJudul() + '\'' +
                ", ringkasan='" + getRingkasan() + '\'' +
                ", tahunUsulan='" + getTahunUsulan() + '\'' +
                ", tahunMulaiPelaksanaan='" + getTahunMulaiPelaksanaan() + '\'' +
                ", tahunSelesaiPelaksanaan='" + getTahunSelesaiPelaksanaan() + '\'' +
                ", latarBelakang='" + getLatarBelakang() + '\'' +
                ", tinjauanPustaka='" + getTinjauanPustaka() + '\'' +
                ", metode='" + getMetode() + '\'' +
                ", daftarPustaka='" + getDaftarPustaka() + '\'' +
                ", idReviewerSkema=" + getIdReviewerSkema() +
                ", lokasiPenelitian='" + getLokasiPenelitian() + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    
}
