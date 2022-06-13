package com.pusilkom.base.dto;

import com.pusilkom.base.model.Buku;
import com.pusilkom.base.model.JadwalKegiatan;
import com.pusilkom.base.model.Output;
import com.pusilkom.base.model.PenulisOutput;

/**
 * Created by ars on 04/28/2020.
 */
public class JadwalKegiatanDTO extends JadwalKegiatan {
    String username;

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
                ", idPenelitian=" + getIdPenelitian() +
                ", tanggalAwalJadwal=" + getTanggalAwalJadwal() +
                ", tanggalAkhirJadwal=" + getTanggalAkhirJadwal() +
                ", tahun=" + getTahun() +
                ", namaKegiatan='" + getNamaKegiatan() + '\'' +
                '}';
    }
}
