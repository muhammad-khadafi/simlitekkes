package com.pusilkom.base.dto;

import java.util.List;

/**
 * Created by ars
 */
public class ProdiParentDiktiDTO {
    private String status;
    private String message;
    private List<ProdiDiktiDTO> data;

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

    public List<ProdiDiktiDTO> getData() {
        return data;
    }

    public void setData(List<ProdiDiktiDTO> data) {
        this.data = data;
    }
}
