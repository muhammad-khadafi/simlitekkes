package com.pusilkom.base.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by ars
 */
public class PenelitiParentDiktiDTO {
    private String status;
    private String message;
    private List<PenelitiDiktiDTO> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PenelitiDiktiDTO> getData() {
        return data;
    }

    public void setData(List<PenelitiDiktiDTO> data) {
        this.data = data;
    }
}
