/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.dto;

import com.pusilkom.base.model.RencanaAnggaran;

import java.math.BigDecimal;

/**
 *
 * @author Helmi Fakhriandi
 */
public class RencanaAnggaranDTO extends RencanaAnggaran {
    private String namaKomponen;
    private String deskripsiKomponen;
    private Long totalBiaya;
    private BigDecimal subTotal;
    private String subTotalFormatted;
    private String biayaSatuanFormatted;

    public String getSubTotalFormatted() {
        return subTotalFormatted;
    }

    public void setSubTotalFormatted(String subTotalFormatted) {
        this.subTotalFormatted = subTotalFormatted;
    }

    public String getBiayaSatuanFormatted() {
        return biayaSatuanFormatted;
    }

    public void setBiayaSatuanFormatted(String biayaSatuanFormatted) {
        this.biayaSatuanFormatted = biayaSatuanFormatted;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public String getNamaKomponen() {
        return namaKomponen;
    }

    public void setNamaKomponen(String namaKomponen) {
        this.namaKomponen = namaKomponen;
    }

    public String getDeskripsiKomponen() {
        return deskripsiKomponen;
    }

    public void setDeskripsiKomponen(String deskripsiKomponen) {
        this.deskripsiKomponen = deskripsiKomponen;
    }

    public Long getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(Long totalBiaya) {
        this.totalBiaya = totalBiaya;
    }
}
