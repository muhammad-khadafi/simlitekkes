package com.pusilkom.base.dto;

import com.pusilkom.base.model.Organisasi;

public class OrganisasiDTO extends Organisasi {

    private String username;
    private String temaUnggulan;
    private String topikUnggulan;
    private Integer idTema;

    public Integer getIdTema() {
        return idTema;
    }

    public void setIdTema(Integer idTema) {
        this.idTema = idTema;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTemaUnggulan() {
        return temaUnggulan;
    }

    public void setTemaUnggulan(String temaUnggulan) {
        this.temaUnggulan = temaUnggulan;
    }

    public String getTopikUnggulan() {
        return topikUnggulan;
    }

    public void setTopikUnggulan(String topikUnggulan) {
        this.topikUnggulan = topikUnggulan;
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", id=" + getId() +
                ", idTopik=" + getIdTopik() +
                ", nama='" + getNama() + '\'' +
                ", noSkPendirian='" + getNoSkPendirian() + '\'' +
                ", alamat='" + getAlamat() + '\'' +
                ", noTelp='" + getNoTelp() + '\'' +
                ", noFax='" + getNoFax() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", namaJabatanKepala='" + getNamaJabatanKepala() + '\'' +
                ", nidnPejabatKepala='" + getNidnPejabatKepala() + '\'' +
                ", namaPejabatKepala='" + getNamaPejabatKepala() + '\'' +
                ", jenisKelamin='" + getJenisKelamin() + '\'' +
                ", jenjangPendidikan='" + getJenjangPendidikan() + '\'' +
                ", isPoltekkes=" + getIsPoltekkes() +
                ", klasifikasi=" + getKlasifikasi() +
                ", website='" + getWebsite() + '\'' +
                '}';
    }
}
