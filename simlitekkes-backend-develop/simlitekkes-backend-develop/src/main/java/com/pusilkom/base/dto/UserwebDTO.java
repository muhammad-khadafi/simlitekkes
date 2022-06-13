package com.pusilkom.base.dto;

import com.pusilkom.base.model.Userweb;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * Created by muhammad.khadafi on 20/02/2020.
 */
public class UserwebDTO extends Userweb {

    private String userId;
    private String organisasi;
    private String lastLoginStr;
    private MultipartFile[] fotoFormData;
    private String usernameSubmitter;

    public String getUsernameSubmitter() {
        return usernameSubmitter;
    }

    public void setUsernameSubmitter(String usernameSubmitter) {
        this.usernameSubmitter = usernameSubmitter;
    }

    public MultipartFile[] getFotoFormData() {
        return fotoFormData;
    }

    public void setFotoFormData(MultipartFile[] fotoFormData) {
        this.fotoFormData = fotoFormData;
    }

    public String getOrganisasi() {
        return organisasi;
    }

    public void setOrganisasi(String organisasi) {
        this.organisasi = organisasi;
    }

    public String getLastLoginStr() {
        return lastLoginStr;
    }

    public void setLastLoginStr(String lastLoginStr) {
        this.lastLoginStr = lastLoginStr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", lastLogin=" + getLastLogin() +
                ", foto='" + getFoto() + '\'' +
                ", nip='" + getNip() + '\'' +
                ", nama='" + getNama() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", isActive=" + getIsActive() +
                ", idOrganisasi=" + getIdOrganisasi() +
                ", fotoName='" + getFotoName() + '\'' +
                ", userId='" + userId + '\'' +
                ", organisasi='" + organisasi + '\'' +
                ", lastLoginStr='" + lastLoginStr + '\'' +
                ", fotoFormData=" + Arrays.toString(fotoFormData) +
                '}';
    }

}
