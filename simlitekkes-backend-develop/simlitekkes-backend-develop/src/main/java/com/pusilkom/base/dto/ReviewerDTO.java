package com.pusilkom.base.dto;

import com.pusilkom.base.model.ReviewerSkema;

public class ReviewerDTO extends ReviewerSkema {
    private String namaInstitusi;
    private String namaPeneliti;
    private String nidnPeneliti;
    private String nomorDokumen;
    private Integer jumlahProposalDiPlot;
    private Integer bebanReviewMaksimal;
    private String username;
    private String judulDokumen;

    public String getNamaInstitusi() {
        return namaInstitusi;
    }

    public void setNamaInstitusi(String namaInstitusi) {
        this.namaInstitusi = namaInstitusi;
    }

    public String getNamaPeneliti() {
        return namaPeneliti;
    }

    public void setNamaPeneliti(String namaPeneliti) {
        this.namaPeneliti = namaPeneliti;
    }

    public String getNidnPeneliti() {
        return nidnPeneliti;
    }

    public void setNidnPeneliti(String nidnPeneliti) {
        this.nidnPeneliti = nidnPeneliti;
    }

    public String getNomorDokumen() {
        return nomorDokumen;
    }

    public void setNomorDokumen(String nomorDokumen) {
        this.nomorDokumen = nomorDokumen;
    }

    public Integer getJumlahProposalDiPlot() {
        return jumlahProposalDiPlot;
    }

    public void setJumlahProposalDiPlot(Integer jumlahProposalDiPlot) {
        this.jumlahProposalDiPlot = jumlahProposalDiPlot;
    }

    public Integer getBebanReviewMaksimal() {
        return bebanReviewMaksimal;
    }

    public void setBebanReviewMaksimal(Integer bebanReviewMaksimal) {
        this.bebanReviewMaksimal = bebanReviewMaksimal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJudulDokumen() {
        return judulDokumen;
    }

    public void setJudulDokumen(String judulDokumen) {
        this.judulDokumen = judulDokumen;
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", id=" + getId() +
                ", idPeneliti=" + getIdPeneliti() +
                ", idOrganisasi=" + getIdOrganisasi() +
                ", idPemberiTugas=" + getIdPemberiTugas() +
                ", tahunUsulan='" + getTahunUsulan() + '\'' +
                ", tahunPelaksanaan='" + getTahunPelaksanaan() + '\'' +
                ", isActive=" + getIsActive() +
                ", isReviewerEksternal=" + getIsReviewerEksternal() +
                ", isNew=" + getIsNew() +
                ", isSeen=" + getIsSeen() +
                ", idDokumenOrganisasi=" + getIdDokumenOrganisasi() +
                '}';
    }
}
