package com.pusilkom.base.dto;

public class ForgotPasswordDTO {
    private String email;
    private boolean isValid;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsValid() {
        return this.isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

}