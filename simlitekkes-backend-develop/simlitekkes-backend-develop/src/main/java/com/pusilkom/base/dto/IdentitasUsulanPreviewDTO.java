package com.pusilkom.base.dto;

import com.pusilkom.base.model.Berita;

/**
 * Created by ars on 04/03/2020.
 */
public class IdentitasUsulanPreviewDTO {
    private Integer id;
    private String judul;
    private String skema;
    private String namaKategori;
    private String namaTkt;
    private Integer levelSaatIni;
    private Integer levelTarget;
    private String topik;
    private String tema;
    private String rumpunIlmu;
    private String lokasiPenelitian;
    private Integer tahunMulaiPelaksanaan;
    private Integer tahunSelesaiPelaksanaan;

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

    public String getSkema() {
        return skema;
    }

    public void setSkema(String skema) {
        this.skema = skema;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public String getNamaTkt() {
        return namaTkt;
    }

    public void setNamaTkt(String namaTkt) {
        this.namaTkt = namaTkt;
    }

    public Integer getLevelSaatIni() {
        return levelSaatIni;
    }

    public void setLevelSaatIni(Integer levelSaatIni) {
        this.levelSaatIni = levelSaatIni;
    }

    public Integer getLevelTarget() {
        return levelTarget;
    }

    public void setLevelTarget(Integer levelTarget) {
        this.levelTarget = levelTarget;
    }

    public String getTopik() {
        return topik;
    }

    public void setTopik(String topik) {
        this.topik = topik;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getRumpunIlmu() {
        return rumpunIlmu;
    }

    public void setRumpunIlmu(String rumpunIlmu) {
        this.rumpunIlmu = rumpunIlmu;
    }

    public String getLokasiPenelitian() {
        return lokasiPenelitian;
    }

    public void setLokasiPenelitian(String lokasiPenelitian) {
        this.lokasiPenelitian = lokasiPenelitian;
    }

    public Integer getTahunMulaiPelaksanaan() {
        return tahunMulaiPelaksanaan;
    }

    public void setTahunMulaiPelaksanaan(Integer tahunMulaiPelaksanaan) {
        this.tahunMulaiPelaksanaan = tahunMulaiPelaksanaan;
    }

    public Integer getTahunSelesaiPelaksanaan() {
        return tahunSelesaiPelaksanaan;
    }

    public void setTahunSelesaiPelaksanaan(Integer tahunSelesaiPelaksanaan) {
        this.tahunSelesaiPelaksanaan = tahunSelesaiPelaksanaan;
    }
}