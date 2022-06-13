package com.pusilkom.base.dto;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by ars
 */
public class PenelitiDiktiDTO {
    private String id;
    private String nidn;
    private String nip;
    private String npwp;
    private String nik;
    private String nama;
    private String gelar_depan;
    private String gelar_belakang;
    private String pendidikan_terakhir;
    private String jenis_kelamin;
    private Date tgl_lahir;
    private String tempat_lahir;
    private String telepon;
    private String handphone;
    private String email;
    private String kode_pt;
    private String kode_prodi;
    private String sk_cpns;
    private Date tgl_sk_cpns;
    private String sk_pengangkatan;
    private Date tgl_sk_pengangkatan;
    private String tmt_sk_pengangkatan;
    private String tmt_pns;
    private String kewarganegaraan;
    private String last_update;
    private AlamatPenelitiDiktiDTO alamat;
    private IdNamaDiktiDTO agama;
    private IdNamaDiktiDTO ikatan_kerja;
    private IdNamaDiktiDTO status_kepegawaian;
    private IdNamaDiktiDTO status_keaktifan;
    private IdNamaDiktiDTO jabatan_fungsional;
    private PangkatGolonganDTO pangkat_golongan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGelar_depan() {
        return gelar_depan;
    }

    public void setGelar_depan(String gelar_depan) {
        this.gelar_depan = gelar_depan;
    }

    public String getGelar_belakang() {
        return gelar_belakang;
    }

    public void setGelar_belakang(String gelar_belakang) {
        this.gelar_belakang = gelar_belakang;
    }

    public String getPendidikan_terakhir() {
        return pendidikan_terakhir;
    }

    public void setPendidikan_terakhir(String pendidikan_terakhir) {
        this.pendidikan_terakhir = pendidikan_terakhir;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getHandphone() {
        return handphone;
    }

    public void setHandphone(String handphone) {
        this.handphone = handphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKode_pt() {
        return kode_pt;
    }

    public void setKode_pt(String kode_pt) {
        this.kode_pt = kode_pt;
    }

    public String getKode_prodi() {
        return kode_prodi;
    }

    public void setKode_prodi(String kode_prodi) {
        this.kode_prodi = kode_prodi;
    }

    public String getSk_cpns() {
        return sk_cpns;
    }

    public void setSk_cpns(String sk_cpns) {
        this.sk_cpns = sk_cpns;
    }

    public Date getTgl_sk_cpns() {
        return tgl_sk_cpns;
    }

    public void setTgl_sk_cpns(Date tgl_sk_cpns) {
        this.tgl_sk_cpns = tgl_sk_cpns;
    }

    public String getSk_pengangkatan() {
        return sk_pengangkatan;
    }

    public void setSk_pengangkatan(String sk_pengangkatan) {
        this.sk_pengangkatan = sk_pengangkatan;
    }

    public Date getTgl_sk_pengangkatan() {
        return tgl_sk_pengangkatan;
    }

    public void setTgl_sk_pengangkatan(Date tgl_sk_pengangkatan) {
        this.tgl_sk_pengangkatan = tgl_sk_pengangkatan;
    }

    public String getTmt_sk_pengangkatan() {
        return tmt_sk_pengangkatan;
    }

    public void setTmt_sk_pengangkatan(String tmt_sk_pengangkatan) {
        this.tmt_sk_pengangkatan = tmt_sk_pengangkatan;
    }

    public String getTmt_pns() {
        return tmt_pns;
    }

    public void setTmt_pns(String tmt_pns) {
        this.tmt_pns = tmt_pns;
    }

    public String getKewarganegaraan() {
        return kewarganegaraan;
    }

    public void setKewarganegaraan(String kewarganegaraan) {
        this.kewarganegaraan = kewarganegaraan;
    }

    public AlamatPenelitiDiktiDTO getAlamat() {
        return alamat;
    }

    public void setAlamat(AlamatPenelitiDiktiDTO alamat) {
        this.alamat = alamat;
    }

    public IdNamaDiktiDTO getAgama() {
        return agama;
    }

    public void setAgama(IdNamaDiktiDTO agama) {
        this.agama = agama;
    }

    public IdNamaDiktiDTO getIkatan_kerja() {
        return ikatan_kerja;
    }

    public void setIkatan_kerja(IdNamaDiktiDTO ikatan_kerja) {
        this.ikatan_kerja = ikatan_kerja;
    }

    public IdNamaDiktiDTO getStatus_kepegawaian() {
        return status_kepegawaian;
    }

    public void setStatus_kepegawaian(IdNamaDiktiDTO status_kepegawaian) {
        this.status_kepegawaian = status_kepegawaian;
    }

    public IdNamaDiktiDTO getStatus_keaktifan() {
        return status_keaktifan;
    }

    public void setStatus_keaktifan(IdNamaDiktiDTO status_keaktifan) {
        this.status_keaktifan = status_keaktifan;
    }

    public IdNamaDiktiDTO getJabatan_fungsional() {
        return jabatan_fungsional;
    }

    public void setJabatan_fungsional(IdNamaDiktiDTO jabatan_fungsional) {
        this.jabatan_fungsional = jabatan_fungsional;
    }

    public PangkatGolonganDTO getPangkat_golongan() {
        return pangkat_golongan;
    }

    public void setPangkat_golongan(PangkatGolonganDTO pangkat_golongan) {
        this.pangkat_golongan = pangkat_golongan;
    }

    public Date getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(Date tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}
