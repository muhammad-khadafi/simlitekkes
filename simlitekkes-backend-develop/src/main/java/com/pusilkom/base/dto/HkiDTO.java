package com.pusilkom.base.dto;

import com.pusilkom.base.model.Hki;
import com.pusilkom.base.model.Output;
import com.pusilkom.base.model.PenulisOutput;

/**
 * Created by ars on 04/02/2020.
 */
public class HkiDTO extends Hki {
    private String judul;
    private String username;
    private String pathFile;
    private String namaJenisOutput;
    private String namaPeranPenulis;
    private Integer idUser;
    private Integer idPenulisOutput;
    private Output output;
    private PenulisOutput penulisOutput;
    private String fileName;
    private String statusHkiString;
    private String urlOutput;
    private String idPenelitian;
    private Integer idPeranPeneliti;

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getNamaJenisOutput() {
        return namaJenisOutput;
    }

    public void setNamaJenisOutput(String namaJenisOutput) {
        this.namaJenisOutput = namaJenisOutput;
    }

    public String getNamaPeranPenulis() {
        return namaPeranPenulis;
    }

    public void setNamaPeranPenulis(String namaPeranPenulis) {
        this.namaPeranPenulis = namaPeranPenulis;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public PenulisOutput getPenulisOutput() {
        return penulisOutput;
    }

    public void setPenulisOutput(PenulisOutput penulisOutput) {
        this.penulisOutput = penulisOutput;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdPenulisOutput() {
        return idPenulisOutput;
    }

    public void setIdPenulisOutput(Integer idPenulisOutput) {
        this.idPenulisOutput = idPenulisOutput;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatusHkiString() {
        return statusHkiString;
    }

    public void setStatusHkiString(String statusHkiString) {
        this.statusHkiString = statusHkiString;
    }

    public String getUrlOutput() {
        return urlOutput;
    }

    public void setUrlOutput(String urlOutput) {
        this.urlOutput = urlOutput;
    }

    public String getIdPenelitian() {
        return idPenelitian;
    }

    public void setIdPenelitian(String idPenelitian) {
        this.idPenelitian = idPenelitian;
    }

    public Integer getIdPeranPeneliti() {
        return idPeranPeneliti;
    }

    public void setIdPeranPeneliti(Integer idPeranPeneliti) {
        this.idPeranPeneliti = idPeranPeneliti;
    }

    @Override
    public String toString() {
        return "{" +
                "idOutput=" + getIdOutput() +
                ", tahunPelaksanaan='" + getTahunPelaksanaan() + '\'' +
                ", noPendaftaran='" + getNoPendaftaran() + '\'' +
                ", statusHki='" + getStatusHki() + '\'' +
                ", noHki='" + getNoHki() + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
