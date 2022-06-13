package com.pusilkom.base.model;

import java.io.Serializable;
import java.util.Date;

public class Output implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column output.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column output.id_jenis_output
     *
     * @mbg.generated
     */
    private Integer idJenisOutput;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column output.id_penelitian
     *
     * @mbg.generated
     */
    private Integer idPenelitian;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column output.judul
     *
     * @mbg.generated
     */
    private String judul;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column output.path_file
     *
     * @mbg.generated
     */
    private String pathFile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column output.timestamp_upload
     *
     * @mbg.generated
     */
    private Date timestampUpload;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column output.media_publikasi
     *
     * @mbg.generated
     */
    private String mediaPublikasi;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column output.tanggal_publikasi
     *
     * @mbg.generated
     */
    private Date tanggalPublikasi;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column output.url
     *
     * @mbg.generated
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table output
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column output.id
     *
     * @return the value of output.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column output.id
     *
     * @param id the value for output.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column output.id_jenis_output
     *
     * @return the value of output.id_jenis_output
     *
     * @mbg.generated
     */
    public Integer getIdJenisOutput() {
        return idJenisOutput;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column output.id_jenis_output
     *
     * @param idJenisOutput the value for output.id_jenis_output
     *
     * @mbg.generated
     */
    public void setIdJenisOutput(Integer idJenisOutput) {
        this.idJenisOutput = idJenisOutput;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column output.id_penelitian
     *
     * @return the value of output.id_penelitian
     *
     * @mbg.generated
     */
    public Integer getIdPenelitian() {
        return idPenelitian;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column output.id_penelitian
     *
     * @param idPenelitian the value for output.id_penelitian
     *
     * @mbg.generated
     */
    public void setIdPenelitian(Integer idPenelitian) {
        this.idPenelitian = idPenelitian;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column output.judul
     *
     * @return the value of output.judul
     *
     * @mbg.generated
     */
    public String getJudul() {
        return judul;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column output.judul
     *
     * @param judul the value for output.judul
     *
     * @mbg.generated
     */
    public void setJudul(String judul) {
        this.judul = judul;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column output.path_file
     *
     * @return the value of output.path_file
     *
     * @mbg.generated
     */
    public String getPathFile() {
        return pathFile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column output.path_file
     *
     * @param pathFile the value for output.path_file
     *
     * @mbg.generated
     */
    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column output.timestamp_upload
     *
     * @return the value of output.timestamp_upload
     *
     * @mbg.generated
     */
    public Date getTimestampUpload() {
        return timestampUpload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column output.timestamp_upload
     *
     * @param timestampUpload the value for output.timestamp_upload
     *
     * @mbg.generated
     */
    public void setTimestampUpload(Date timestampUpload) {
        this.timestampUpload = timestampUpload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column output.media_publikasi
     *
     * @return the value of output.media_publikasi
     *
     * @mbg.generated
     */
    public String getMediaPublikasi() {
        return mediaPublikasi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column output.media_publikasi
     *
     * @param mediaPublikasi the value for output.media_publikasi
     *
     * @mbg.generated
     */
    public void setMediaPublikasi(String mediaPublikasi) {
        this.mediaPublikasi = mediaPublikasi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column output.tanggal_publikasi
     *
     * @return the value of output.tanggal_publikasi
     *
     * @mbg.generated
     */
    public Date getTanggalPublikasi() {
        return tanggalPublikasi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column output.tanggal_publikasi
     *
     * @param tanggalPublikasi the value for output.tanggal_publikasi
     *
     * @mbg.generated
     */
    public void setTanggalPublikasi(Date tanggalPublikasi) {
        this.tanggalPublikasi = tanggalPublikasi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column output.url
     *
     * @return the value of output.url
     *
     * @mbg.generated
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column output.url
     *
     * @param url the value for output.url
     *
     * @mbg.generated
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table output
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Output other = (Output) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIdJenisOutput() == null ? other.getIdJenisOutput() == null : this.getIdJenisOutput().equals(other.getIdJenisOutput()))
            && (this.getIdPenelitian() == null ? other.getIdPenelitian() == null : this.getIdPenelitian().equals(other.getIdPenelitian()))
            && (this.getJudul() == null ? other.getJudul() == null : this.getJudul().equals(other.getJudul()))
            && (this.getPathFile() == null ? other.getPathFile() == null : this.getPathFile().equals(other.getPathFile()))
            && (this.getTimestampUpload() == null ? other.getTimestampUpload() == null : this.getTimestampUpload().equals(other.getTimestampUpload()))
            && (this.getMediaPublikasi() == null ? other.getMediaPublikasi() == null : this.getMediaPublikasi().equals(other.getMediaPublikasi()))
            && (this.getTanggalPublikasi() == null ? other.getTanggalPublikasi() == null : this.getTanggalPublikasi().equals(other.getTanggalPublikasi()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table output
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIdJenisOutput() == null) ? 0 : getIdJenisOutput().hashCode());
        result = prime * result + ((getIdPenelitian() == null) ? 0 : getIdPenelitian().hashCode());
        result = prime * result + ((getJudul() == null) ? 0 : getJudul().hashCode());
        result = prime * result + ((getPathFile() == null) ? 0 : getPathFile().hashCode());
        result = prime * result + ((getTimestampUpload() == null) ? 0 : getTimestampUpload().hashCode());
        result = prime * result + ((getMediaPublikasi() == null) ? 0 : getMediaPublikasi().hashCode());
        result = prime * result + ((getTanggalPublikasi() == null) ? 0 : getTanggalPublikasi().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        return result;
    }
}