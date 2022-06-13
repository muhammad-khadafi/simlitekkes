package com.pusilkom.base.dto;

import com.pusilkom.base.model.LogAktivitas;

public class LogAktifitasDTO extends LogAktivitas {
    private String waktuStr;
    private String shortRequest;

    public String getWaktuStr() {
        return waktuStr;
    }

    public void setWaktuStr(String waktuStr) {
        this.waktuStr = waktuStr;
    }

    public String getShortRequest() {
        return shortRequest;
    }

    public void setShortRequest(String shortRequest) {
        this.shortRequest = shortRequest;
    }
}
