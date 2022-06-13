package com.pusilkom.base.dto;

import java.util.Date;

/**
 * Created by ars
 */
public class ProdiDiktiDTO {
    private String id;
    private String kode;
    private String nama;
    private String status;
    private String visi;
    private String misi;
    private String kompetensi;
    private String jalan;
    private String telepon;
    private String faksimile;
    private String website;
    private String email;
    private Date tgl_berdiri;
    private String sk_selenggara;
    private String tgl_sk_selenggara;
    private Integer sks_lulus;
    private Integer kode_pos;
    private Date last_update;
    private IdNamaDiktiDTO kab_kota;
    private IdNamaDiktiDTO jenjang_didik;
    private PerguruanTinggiDiktiDTO pt;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVisi() {
        return visi;
    }

    public void setVisi(String visi) {
        this.visi = visi;
    }

    public String getMisi() {
        return misi;
    }

    public void setMisi(String misi) {
        this.misi = misi;
    }

    public String getKompetensi() {
        return kompetensi;
    }

    public void setKompetensi(String kompetensi) {
        this.kompetensi = kompetensi;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
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

    public Date getTgl_berdiri() {
        return tgl_berdiri;
    }

    public void setTgl_berdiri(Date tgl_berdiri) {
        this.tgl_berdiri = tgl_berdiri;
    }

    public String getSk_selenggara() {
        return sk_selenggara;
    }

    public void setSk_selenggara(String sk_selenggara) {
        this.sk_selenggara = sk_selenggara;
    }

    public String getTgl_sk_selenggara() {
        return tgl_sk_selenggara;
    }

    public void setTgl_sk_selenggara(String tgl_sk_selenggara) {
        this.tgl_sk_selenggara = tgl_sk_selenggara;
    }

    public Integer getSks_lulus() {
        return sks_lulus;
    }

    public void setSks_lulus(Integer sks_lulus) {
        this.sks_lulus = sks_lulus;
    }

    public Integer getKode_pos() {
        return kode_pos;
    }

    public void setKode_pos(Integer kode_pos) {
        this.kode_pos = kode_pos;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public IdNamaDiktiDTO getKab_kota() {
        return kab_kota;
    }

    public void setKab_kota(IdNamaDiktiDTO kab_kota) {
        this.kab_kota = kab_kota;
    }

    public IdNamaDiktiDTO getJenjang_didik() {
        return jenjang_didik;
    }

    public void setJenjang_didik(IdNamaDiktiDTO jenjang_didik) {
        this.jenjang_didik = jenjang_didik;
    }

    public PerguruanTinggiDiktiDTO getPt() {
        return pt;
    }

    public void setPt(PerguruanTinggiDiktiDTO pt) {
        this.pt = pt;
    }
}
