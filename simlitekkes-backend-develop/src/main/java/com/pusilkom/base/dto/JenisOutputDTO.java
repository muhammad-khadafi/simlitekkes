package com.pusilkom.base.dto;

import com.pusilkom.base.model.JenisOutput;
import com.pusilkom.base.model.Jurnal;
import com.pusilkom.base.model.Output;
import com.pusilkom.base.model.PenulisOutput;

/**
 * Created by ars on 23/04/2020.
 */
public class JenisOutputDTO extends JenisOutput {
    String username;
    Integer jumlahOutput;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getJumlahOutput() {
        return jumlahOutput;
    }

    public void setJumlahOutput(Integer jumlahOutput) {
        this.jumlahOutput = jumlahOutput;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + getId() +
                ", kelompokOutput='" + getKelompokOutput() + '\'' +
                ", nama='" + getNama() + '\'' +
                ", username='" + username +
                '}';
    }
}
