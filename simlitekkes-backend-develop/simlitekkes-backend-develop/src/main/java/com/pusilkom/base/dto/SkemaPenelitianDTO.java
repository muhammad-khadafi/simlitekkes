package com.pusilkom.base.dto;

import com.pusilkom.base.model.SkemaPenelitian;

import java.util.Date;

/**
 * Created by muhammad.khadafi on 09/10/2019.
 */
public class SkemaPenelitianDTO extends SkemaPenelitian {
    String namaKategori;
    String namaJenisPenelitian;
    String jumlahDanaString;
    String tktString;
    String jangkaWaktuString;
    String jumlahAnggotaString;
    String luaran;
    String username;

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public String getNamaJenisPenelitian() {
        return namaJenisPenelitian;
    }

    public void setNamaJenisPenelitian(String namaJenisPenelitian) {
        this.namaJenisPenelitian = namaJenisPenelitian;
    }

    public String getJumlahDanaString() {
        return jumlahDanaString;
    }

    public void setJumlahDanaString(String jumlahDanaString) {
        this.jumlahDanaString = jumlahDanaString;
    }

    public String getTktString() {
        return tktString;
    }

    public void setTktString(String tktString) {
        this.tktString = tktString;
    }

    public String getJangkaWaktuString() {
        return jangkaWaktuString;
    }

    public void setJangkaWaktuString(String jangkaWaktuString) {
        this.jangkaWaktuString = jangkaWaktuString;
    }

    public String getJumlahAnggotaString() {
        return jumlahAnggotaString;
    }

    public void setJumlahAnggotaString(String jumlahAnggotaString) {
        this.jumlahAnggotaString = jumlahAnggotaString;
    }

    public String getLuaran() {
        return luaran;
    }

    public void setLuaran(String luaran) {
        this.luaran = luaran;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + getId() +
                ", idJenisPenelitian=" + getIdJenisPenelitian() +
                ", nama='" + getNama() + '\'' +
                ", singkatan='" + getSingkatan() + '\'' +
                ", deskripsi='" + getDeskripsi() + '\'' +
                ", klasifikasi1=" + getKlasifikasi1() +
                ", klasifikasi2=" + getKlasifikasi2() +
                ", klasifikasi3=" + getKlasifikasi3() +
                ", persyaratanPenelitian='" + getPersyaratanPenelitian() + '\'' +
                ", tktMin=" + getTktMin() +
                ", tktMax=" + getTktMax() +
                ", jumlahPengusulMin=" + getJumlahPengusulMin() +
                ", jumlahPengusulMax=" + getJumlahPengusulMax() +
                ", jangkaWaktuMin=" + getJangkaWaktuMin() +
                ", jangkaWaktuMax=" + getJangkaWaktuMax() +
                ", jumlahDanaMin=" + getJumlahDanaMin() +
                ", jumlahDanaMax=" + getJumlahDanaMax() +
                ", idKategoriSkemaPenelitian=" + getIdKategoriSkemaPenelitian() +
                ", isDikelolaPusat=" + getIsDikelolaPusat() +
                ", userName='" + username + '\'' +
                '}';
    }
}
