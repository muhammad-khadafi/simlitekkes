package com.pusilkom.base.dto;

import com.pusilkom.base.model.Tema;

/**
 * Created by ars on 03/17/2020.
 */
public class TemaDTO extends Tema {
    String namaKelompokTkt;
    String username;

    public String getNamaKelompokTkt() {
        return namaKelompokTkt;
    }

    public void setNamaKelompokTkt(String namaKelompokTkt) {
        this.namaKelompokTkt = namaKelompokTkt;
    }

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
                ", nama='" + getNama() + '\'' +
                '}';
    }
}
