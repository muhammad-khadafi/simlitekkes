package com.pusilkom.base.dto;

/**
 * Created by ars
 */
public class AlamatPenelitiDiktiDTO {
    private String jalan;
    private String rt;
    private String rw;
    private String dusun;
    private String kelurahan;
    private String kode_pos;
    private IdNamaDiktiDTO kab_kota;

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getDusun() {
        return dusun;
    }

    public void setDusun(String dusun) {
        this.dusun = dusun;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKode_pos() {
        return kode_pos;
    }

    public void setKode_pos(String kode_pos) {
        this.kode_pos = kode_pos;
    }

    public IdNamaDiktiDTO getKab_kota() {
        return kab_kota;
    }

    public void setKab_kota(IdNamaDiktiDTO kab_kota) {
        this.kab_kota = kab_kota;
    }
}
