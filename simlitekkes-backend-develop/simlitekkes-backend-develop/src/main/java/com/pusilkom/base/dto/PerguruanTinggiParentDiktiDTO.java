package com.pusilkom.base.dto;

import java.util.List;

/**
 * Created by ars
 */
public class PerguruanTinggiParentDiktiDTO {
    private String status;
    private String message;
    private List<PerguruanTinggiDiktiDTO> data;

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

    public List<PerguruanTinggiDiktiDTO> getData() {
        return data;
    }

    public void setData(List<PerguruanTinggiDiktiDTO> data) {
        this.data = data;
    }
}
