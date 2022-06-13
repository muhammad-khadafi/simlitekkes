package com.pusilkom.base.dto;

import java.math.BigDecimal;

public class LaporanAnggaranDTO {
    private String peneliti;
    private String nidn;
    private String institusi;
    private String skema;
    private String judulPenelitian;
    private BigDecimal biaya;
    private BigDecimal danaKontribusiMitra;
    private String barangKontribusiMitra;

    public String getPeneliti() {
        return peneliti;
    }

    public void setPeneliti(String peneliti) {
        this.peneliti = peneliti;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getInstitusi() {
        return institusi;
    }

    public void setInstitusi(String institusi) {
        this.institusi = institusi;
    }

    public String getSkema() {
        return skema;
    }

    public void setSkema(String skema) {
        this.skema = skema;
    }

    public String getJudulPenelitian() {
        return judulPenelitian;
    }

    public void setJudulPenelitian(String judulPenelitian) {
        this.judulPenelitian = judulPenelitian;
    }

    public BigDecimal getBiaya() {
        return biaya;
    }

    public void setBiaya(BigDecimal biaya) {
        this.biaya = biaya;
    }

    public BigDecimal getDanaKontribusiMitra() {
        return danaKontribusiMitra;
    }

    public void setDanaKontribusiMitra(BigDecimal danaKontribusiMitra) {
        this.danaKontribusiMitra = danaKontribusiMitra;
    }

    public String getBarangKontribusiMitra() {
        return barangKontribusiMitra;
    }

    public void setBarangKontribusiMitra(String barangKontribusiMitra) {
        this.barangKontribusiMitra = barangKontribusiMitra;
    }
}
