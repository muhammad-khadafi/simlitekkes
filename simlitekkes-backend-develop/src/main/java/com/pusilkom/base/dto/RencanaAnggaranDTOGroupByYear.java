package com.pusilkom.base.dto;


import java.math.BigDecimal;
import java.util.List;

public class RencanaAnggaranDTOGroupByYear {

    private String tahun;
    private String totalBiaya;
    private Long totalBiayaNum;

    public Long getTotalBiayaNum() {
        return totalBiayaNum;
    }

    public void setTotalBiayaNum(Long totalBiayaNum) {
        this.totalBiayaNum = totalBiayaNum;
    }

    private List<RencanaAnggaranDTO> rencanaAnggaranListPerYear;

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(String totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public List<RencanaAnggaranDTO> getRencanaAnggaranListPerYear() {
        return rencanaAnggaranListPerYear;
    }

    public void setRencanaAnggaranListPerYear(List<RencanaAnggaranDTO> rencanaAnggaranListPerYear) {
        this.rencanaAnggaranListPerYear = rencanaAnggaranListPerYear;
    }
}
