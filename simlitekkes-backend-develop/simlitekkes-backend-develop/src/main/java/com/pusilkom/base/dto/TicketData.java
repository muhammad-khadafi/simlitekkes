package com.pusilkom.base.dto;

import java.util.Date;

/**
 * Created by muhammad.khadafi on 4/18/17.
 */
public class TicketData {
    private String requestUri;
    private Date dateRequest;

    public TicketData() {
    }

    public TicketData(String requestUri, Date dateRequest) {
        this.requestUri = requestUri;
        this.dateRequest = dateRequest;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }
}
