package com.pusilkom.base.dto;

/**
 * Created by ars
 */
public class PangkatGolonganDTO {
    private Integer id;
    private String golongan;
    private String pangkat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public String getPangkat() {
        return pangkat;
    }

    public void setPangkat(String pangkat) {
        this.pangkat = pangkat;
    }
}
