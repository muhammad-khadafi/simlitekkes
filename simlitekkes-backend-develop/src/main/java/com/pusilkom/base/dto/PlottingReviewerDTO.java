package com.pusilkom.base.dto;

public class PlottingReviewerDTO {
    private Integer idPenelitian;
    private String judulPenelitian;
    private Integer idKetuaTim;
    private String namaKetuaTim;
    private String institusiKetuaTim;
    private Integer idReviewer;
    private String namaReviewer;
    private String institusiReviewer;
    private Integer idReviewerSkema;
    private String username;
    private String tahunUsulan;
    private String statusPenelitian;

    public Integer getIdPenelitian() {
        return idPenelitian;
    }

    public void setIdPenelitian(Integer idPenelitian) {
        this.idPenelitian = idPenelitian;
    }

    public String getJudulPenelitian() {
        return judulPenelitian;
    }

    public void setJudulPenelitian(String judulPenelitian) {
        this.judulPenelitian = judulPenelitian;
    }

    public Integer getIdKetuaTim() {
        return idKetuaTim;
    }

    public void setIdKetuaTim(Integer idKetuaTim) {
        this.idKetuaTim = idKetuaTim;
    }

    public String getNamaKetuaTim() {
        return namaKetuaTim;
    }

    public void setNamaKetuaTim(String namaKetuaTim) {
        this.namaKetuaTim = namaKetuaTim;
    }

    public String getInstitusiKetuaTim() {
        return institusiKetuaTim;
    }

    public void setInstitusiKetuaTim(String institusiKetuaTim) {
        this.institusiKetuaTim = institusiKetuaTim;
    }

    public Integer getIdReviewer() {
        return idReviewer;
    }

    public void setIdReviewer(Integer idReviewer) {
        this.idReviewer = idReviewer;
    }

    public String getNamaReviewer() {
        return namaReviewer;
    }

    public void setNamaReviewer(String namaReviewer) {
        this.namaReviewer = namaReviewer;
    }

    public String getInstitusiReviewer() {
        return institusiReviewer;
    }

    public void setInstitusiReviewer(String institusiReviewer) {
        this.institusiReviewer = institusiReviewer;
    }

    public Integer getIdReviewerSkema() {
        return idReviewerSkema;
    }

    public void setIdReviewerSkema(Integer idReviewerSkema) {
        this.idReviewerSkema = idReviewerSkema;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTahunUsulan() {
        return tahunUsulan;
    }

    public void setTahunUsulan(String tahunUsulan) {
        this.tahunUsulan = tahunUsulan;
    }

    public String getStatusPenelitian() {
        return statusPenelitian;
    }

    public void setStatusPenelitian(String statusPenelitian) {
        this.statusPenelitian = statusPenelitian;
    }

    @Override
    public String toString() {
        return "{" +
                "idPenelitian=" + idPenelitian +
                ", judulPenelitian='" + judulPenelitian + '\'' +
                ", idKetuaTim=" + idKetuaTim +
                ", namaKetuaTim='" + namaKetuaTim + '\'' +
                ", institusiKetuaTim='" + institusiKetuaTim + '\'' +
                ", idReviewer=" + idReviewer +
                ", namaReviewer='" + namaReviewer + '\'' +
                ", institusiReviewer='" + institusiReviewer + '\'' +
                ", idReviewerSkema=" + idReviewerSkema +
                ", username='" + username + '\'' +
                ", tahunUsulan='" + tahunUsulan + '\'' +
                ", statusPenelitian='" + statusPenelitian + '\'' +
                '}';
    }
}
