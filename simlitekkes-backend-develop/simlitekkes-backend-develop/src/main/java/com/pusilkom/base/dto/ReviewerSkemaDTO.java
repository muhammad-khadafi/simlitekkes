package com.pusilkom.base.dto;

import com.pusilkom.base.model.ReviewerSkema;

public class ReviewerSkemaDTO extends ReviewerSkema {
    private String username;
    private String namaReviewer;
    private String nidnReviewer;
    private String bidangReviewer;
    private String institusiReviewer;
    private Integer jumlahPenelitianDiReview;
    private Integer bebanReviewMaksimal;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                '}';
    }

    public String getNamaReviewer() {
        return namaReviewer;
    }

    public void setNamaReviewer(String namaReviewer) {
        this.namaReviewer = namaReviewer;
    }

    public String getNidnReviewer() {
        return nidnReviewer;
    }

    public void setNidnReviewer(String nidnReviewer) {
        this.nidnReviewer = nidnReviewer;
    }

    public String getBidangReviewer() {
        return bidangReviewer;
    }

    public void setBidangReviewer(String bidangReviewer) {
        this.bidangReviewer = bidangReviewer;
    }

    public String getInstitusiReviewer() {
        return institusiReviewer;
    }

    public void setInstitusiReviewer(String institusiReviewer) {
        this.institusiReviewer = institusiReviewer;
    }

    public Integer getJumlahPenelitianDiReview() {
        return jumlahPenelitianDiReview;
    }

    public void setJumlahPenelitianDiReview(Integer jumlahPenelitianDiReview) {
        this.jumlahPenelitianDiReview = jumlahPenelitianDiReview;
    }

    public Integer getBebanReviewMaksimal() {
        return bebanReviewMaksimal;
    }

    public void setBebanReviewMaksimal(Integer bebanReviewMaksimal) {
        this.bebanReviewMaksimal = bebanReviewMaksimal;
    }
}
