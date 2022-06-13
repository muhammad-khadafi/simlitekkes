package com.pusilkom.base.dto;

import com.pusilkom.base.model.IndikatorTkt;

import java.util.List;

public class PengukuranTktDTO {

    private String definisi;

    private List<IndikatorTktDTO> indikatorTktList;

    public List<IndikatorTktDTO> getIndikatorTktList() {
        return indikatorTktList;
    }

    public void setIndikatorTktList(List<IndikatorTktDTO> indikatorTktList) {
        this.indikatorTktList = indikatorTktList;
    }

    public String getDefinisi() {
        return definisi;
    }

    public void setDefinisi(String definisi) {
        this.definisi = definisi;
    }

}
