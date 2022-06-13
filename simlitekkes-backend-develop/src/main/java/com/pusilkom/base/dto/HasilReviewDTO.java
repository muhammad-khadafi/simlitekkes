package com.pusilkom.base.dto;

public class HasilReviewDTO {
    private String bagianUsulan;
    private Integer idPertanyaan;
    private Integer idPilihan;
    private Integer nomorUrut;
    private Integer skorMaksimal;
    private Integer nilai;
    private Integer idJawaban;
    private String pertanyaan;
    private String pilihan;
    private String komentar;
    private Integer idJawabanDipilih;

    public String getBagianUsulan() {
        return bagianUsulan;
    }

    public void setBagianUsulan(String bagianUsulan) {
        this.bagianUsulan = bagianUsulan;
    }

    public Integer getIdPertanyaan() {
        return idPertanyaan;
    }

    public void setIdPertanyaan(Integer idPertanyaan) {
        this.idPertanyaan = idPertanyaan;
    }

    public Integer getIdPilihan() {
        return idPilihan;
    }

    public void setIdPilihan(Integer idPilihan) {
        this.idPilihan = idPilihan;
    }

    public Integer getNomorUrut() {
        return nomorUrut;
    }

    public void setNomorUrut(Integer nomorUrut) {
        this.nomorUrut = nomorUrut;
    }

    public Integer getSkorMaksimal() {
        return skorMaksimal;
    }

    public void setSkorMaksimal(Integer skorMaksimal) {
        this.skorMaksimal = skorMaksimal;
    }

    public Integer getNilai() {
        return nilai;
    }

    public void setNilai(Integer nilai) {
        this.nilai = nilai;
    }

    public Integer getIdJawaban() {
        return idJawaban;
    }

    public void setIdJawaban(Integer idJawaban) {
        this.idJawaban = idJawaban;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getPilihan() {
        return pilihan;
    }

    public void setPilihan(String pilihan) {
        this.pilihan = pilihan;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Integer getIdJawabanDipilih() {
        return idJawabanDipilih;
    }

    public void setIdJawabanDipilih(Integer idJawabanDipilih) {
        this.idJawabanDipilih = idJawabanDipilih;
    }
}
