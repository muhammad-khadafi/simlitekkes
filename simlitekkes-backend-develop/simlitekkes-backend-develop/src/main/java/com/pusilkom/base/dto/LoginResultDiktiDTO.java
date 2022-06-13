package com.pusilkom.base.dto;

import java.util.Date;

/**
 * Created by ars
 */
public class LoginResultDiktiDTO {
    private String status;
    private String token;
    private String username;
    private String expired;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }
}
