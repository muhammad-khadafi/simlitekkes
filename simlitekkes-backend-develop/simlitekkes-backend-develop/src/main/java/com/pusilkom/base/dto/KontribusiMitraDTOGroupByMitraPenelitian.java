/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.dto;

import java.util.List;

/**
 *
 * @author Helmi Fakhriandi
 */
public class KontribusiMitraDTOGroupByMitraPenelitian {
    private String totalDanaMitra;
    private List<String> rekapBarangMitra;

    public String getTotalDanaMitra() {
        return totalDanaMitra;
    }

    public void setTotalDanaMitra(String totalDanaMitra) {
        this.totalDanaMitra = totalDanaMitra;
    }

    public List<String> getRekapBarangMitra() {
        return rekapBarangMitra;
    }

    public void setRekapBarangMitra(List<String> rekapBarangMitra) {
        this.rekapBarangMitra = rekapBarangMitra;
    }
    
}
