package com.pusilkom.base.dto;

import com.pusilkom.base.model.PenulisOutput;

/**
 * Created by ars on 04/02/2020.
 */
public class PenulisOutputDTO extends PenulisOutput {
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
                "id=" + getId() +
                ", idPeranPenulisOutput=" + getIdPeranPenulisOutput() +
                ", idOutput=" + getIdOutput() +
                ", idPeneliti=" + getIdPeneliti() +
                ", username='" + username + '\'' +
                '}';
    }
}
