package com.pusilkom.base.dto;

import com.pusilkom.base.model.Buku;
import com.pusilkom.base.model.Output;
import com.pusilkom.base.model.PenulisOutput;
import com.pusilkom.base.model.WorkflowPenelitian;

/**
 * Created by ars on 04/16/2020.
 */
public class WorkflowPenelitianDTO extends WorkflowPenelitian {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "{" +
                "idPenelitian=" + getIdPenelitian() +
                ", idStatusTahapPenelitian=" + getIdStatusTahapPenelitian() +
                ", timestampUpdate=" + getTimestampUpdate() +
                ", isLast=" + getIsLast() +
                ", username='" + getUsername() + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
