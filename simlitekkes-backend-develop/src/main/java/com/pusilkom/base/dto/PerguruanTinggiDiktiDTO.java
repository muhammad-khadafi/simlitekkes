package com.pusilkom.base.dto;

import java.util.Date;

/**
 * Created by ars
 */
public class PerguruanTinggiDiktiDTO {
    private String id;
    private String kode;
    private String nama;
    private String nama_singkat;
    private String sk_pendirian;
    private Date tgl_sk_pendirian;
    private String sk_operasional;
    private Date tgl_sk_operasional;
    private String status;
    private String telepon;
    private String faksimile;
    private String website;
    private String email;
    private Date last_update;
    private IdNamaDiktiDTO propinsi;
    private IdNamaDiktiDTO status_milik;
    private IdNamaDiktiDTO pembina;
    private IdNamaDiktiDTO bentuk_pendidikan;
    private AlamatPenelitiDiktiDTO alamat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama_singkat() {
        return nama_singkat;
    }

    public void setNama_singkat(String nama_singkat) {
        this.nama_singkat = nama_singkat;
    }

    public String getSk_pendirian() {
        return sk_pendirian;
    }

    public void setSk_pendirian(String sk_pendirian) {
        this.sk_pendirian = sk_pendirian;
    }

    public Date getTgl_sk_pendirian() {
        return tgl_sk_pendirian;
    }

    public void setTgl_sk_pendirian(Date tgl_sk_pendirian) {
        this.tgl_sk_pendirian = tgl_sk_pendirian;
    }

    public String getSk_operasional() {
        return sk_operasional;
    }

    public void setSk_operasional(String sk_operasional) {
        this.sk_operasional = sk_operasional;
    }

    public Date getTgl_sk_operasional() {
        return tgl_sk_operasional;
    }

    public void setTgl_sk_operasional(Date tgl_sk_operasional) {
        this.tgl_sk_operasional = tgl_sk_operasional;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getFaksimile() {
        return faksimile;
    }

    public void setFaksimile(String faksimile) {
        this.faksimile = faksimile;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public IdNamaDiktiDTO getPropinsi() {
        return propinsi;
    }

    public void setPropinsi(IdNamaDiktiDTO propinsi) {
        this.propinsi = propinsi;
    }

    public IdNamaDiktiDTO getStatus_milik() {
        return status_milik;
    }

    public void setStatus_milik(IdNamaDiktiDTO status_milik) {
        this.status_milik = status_milik;
    }

    public IdNamaDiktiDTO getPembina() {
        return pembina;
    }

    public void setPembina(IdNamaDiktiDTO pembina) {
        this.pembina = pembina;
    }

    public IdNamaDiktiDTO getBentuk_pendidikan() {
        return bentuk_pendidikan;
    }

    public void setBentuk_pendidikan(IdNamaDiktiDTO bentuk_pendidikan) {
        this.bentuk_pendidikan = bentuk_pendidikan;
    }

    public AlamatPenelitiDiktiDTO getAlamat() {
        return alamat;
    }

    public void setAlamat(AlamatPenelitiDiktiDTO alamat) {
        this.alamat = alamat;
    }
}
