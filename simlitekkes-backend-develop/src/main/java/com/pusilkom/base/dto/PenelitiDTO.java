package com.pusilkom.base.dto;

import com.pusilkom.base.model.Peneliti;

import java.math.BigDecimal;

/**
 * Created by ars on 03/17/2020.
 */
public class PenelitiDTO extends Peneliti {

    private String namaInstitusi;
    private String isActiveStr;
    private String namaBidang;
    private String jenisKelaminStr;
    private String username;
    private String foto;
    private String fotoName;
    private String peranPeneliti;
    private String tugasPenelitian;
    private BigDecimal alokasiJam;
    private Boolean isPoltekkes;
    private String namaUser;

    public BigDecimal getAlokasiJam() {
        return alokasiJam;
    }

    public void setAlokasiJam(BigDecimal alokasiJam) {
        this.alokasiJam = alokasiJam;
    }

    public String getNamaInstitusi() {
        return namaInstitusi;
    }

    public void setNamaInstitusi(String namaInstitusi) {
        this.namaInstitusi = namaInstitusi;
    }

    public String getIsActiveStr() {
        return isActiveStr;
    }

    public void setIsActiveStr(String isActiveStr) {
        this.isActiveStr = isActiveStr;
    }

    public String getNamaBidang() {
        return namaBidang;
    }

    public void setNamaBidang(String namaBidang) {
        this.namaBidang = namaBidang;
    }

    public String getJenisKelaminStr() {
        return jenisKelaminStr;
    }

    public void setJenisKelaminStr(String jenisKelaminStr) {
        this.jenisKelaminStr = jenisKelaminStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFotoName() {
        return fotoName;
    }

    public void setFotoName(String fotoName) {
        this.fotoName = fotoName;
    }

    public String getPeranPeneliti() {
        return peranPeneliti;
    }

    public void setPeranPeneliti(String peranPeneliti) {
        this.peranPeneliti = peranPeneliti;
    }

    public String getTugasPenelitian() {
        return tugasPenelitian;
    }

    public void setTugasPenelitian(String tugasPenelitian) {
        this.tugasPenelitian = tugasPenelitian;
    }

    public Boolean getIsPoltekkes() {
        return isPoltekkes;
    }

    public void setIsPoltekkes(Boolean isPoltekkes) {
        this.isPoltekkes = isPoltekkes;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + getId() +
                ", idUser=" + getIdUser() +
                ", idBidangKeahlian=" + getIdBidangKeahlian() +
                ", idOrganisasi=" + getIdOrganisasi() +
                ", nama='" + getNama() + '\'' +
                ", nidn='" + getNidn() + '\'' +
                ", bebanReviewMaksimal=" + getBebanReviewMaksimal() +
                ", programStudi='" + getProgramStudi() + '\'' +
                ", jenjangPendidikanTerakhir='" + getJenjangPendidikanTerakhir() + '\'' +
                ", jabatanAkademikFungsional='" + getJabatanAkademikFungsional() + '\'' +
                ", gelarDepan='" + getGelarDepan() + '\'' +
                ", gelarBelakang='" + getGelarBelakang() + '\'' +
                ", tempatLahir='" + getTempatLahir() + '\'' +
                ", tanggalLahir=" + getTanggalLahir() +
                ", noKtp='" + getNoKtp() + '\'' +
                ", noTelp='" + getNoTelp() + '\'' +
                ", noHp='" + getNoHp() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", websitePersonal='" + getWebsitePersonal() + '\'' +
                ", alamat='" + getAlamat() + '\'' +
                ", lastSyncTime=" + getLastSyncTime() +
                ", isActive=" + getIsActive() +
                ", jenisKelamin='" + getJenisKelamin() + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

}
