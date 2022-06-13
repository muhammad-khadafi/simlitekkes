package com.pusilkom.base.model;

import java.io.Serializable;
import java.util.Date;

public class Berita implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column berita.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column berita.id_organisasi
     *
     * @mbg.generated
     */
    private Integer idOrganisasi;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column berita.judul
     *
     * @mbg.generated
     */
    private String judul;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column berita.isi
     *
     * @mbg.generated
     */
    private String isi;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column berita.is_published
     *
     * @mbg.generated
     */
    private Boolean isPublished;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column berita.user_publish
     *
     * @mbg.generated
     */
    private String userPublish;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column berita.publish_timestamp
     *
     * @mbg.generated
     */
    private Date publishTimestamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table berita
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column berita.id
     *
     * @return the value of berita.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column berita.id
     *
     * @param id the value for berita.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column berita.id_organisasi
     *
     * @return the value of berita.id_organisasi
     *
     * @mbg.generated
     */
    public Integer getIdOrganisasi() {
        return idOrganisasi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column berita.id_organisasi
     *
     * @param idOrganisasi the value for berita.id_organisasi
     *
     * @mbg.generated
     */
    public void setIdOrganisasi(Integer idOrganisasi) {
        this.idOrganisasi = idOrganisasi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column berita.judul
     *
     * @return the value of berita.judul
     *
     * @mbg.generated
     */
    public String getJudul() {
        return judul;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column berita.judul
     *
     * @param judul the value for berita.judul
     *
     * @mbg.generated
     */
    public void setJudul(String judul) {
        this.judul = judul;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column berita.isi
     *
     * @return the value of berita.isi
     *
     * @mbg.generated
     */
    public String getIsi() {
        return isi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column berita.isi
     *
     * @param isi the value for berita.isi
     *
     * @mbg.generated
     */
    public void setIsi(String isi) {
        this.isi = isi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column berita.is_published
     *
     * @return the value of berita.is_published
     *
     * @mbg.generated
     */
    public Boolean getIsPublished() {
        return isPublished;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column berita.is_published
     *
     * @param isPublished the value for berita.is_published
     *
     * @mbg.generated
     */
    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column berita.user_publish
     *
     * @return the value of berita.user_publish
     *
     * @mbg.generated
     */
    public String getUserPublish() {
        return userPublish;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column berita.user_publish
     *
     * @param userPublish the value for berita.user_publish
     *
     * @mbg.generated
     */
    public void setUserPublish(String userPublish) {
        this.userPublish = userPublish;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column berita.publish_timestamp
     *
     * @return the value of berita.publish_timestamp
     *
     * @mbg.generated
     */
    public Date getPublishTimestamp() {
        return publishTimestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column berita.publish_timestamp
     *
     * @param publishTimestamp the value for berita.publish_timestamp
     *
     * @mbg.generated
     */
    public void setPublishTimestamp(Date publishTimestamp) {
        this.publishTimestamp = publishTimestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table berita
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
        Berita other = (Berita) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIdOrganisasi() == null ? other.getIdOrganisasi() == null : this.getIdOrganisasi().equals(other.getIdOrganisasi()))
            && (this.getJudul() == null ? other.getJudul() == null : this.getJudul().equals(other.getJudul()))
            && (this.getIsi() == null ? other.getIsi() == null : this.getIsi().equals(other.getIsi()))
            && (this.getIsPublished() == null ? other.getIsPublished() == null : this.getIsPublished().equals(other.getIsPublished()))
            && (this.getUserPublish() == null ? other.getUserPublish() == null : this.getUserPublish().equals(other.getUserPublish()))
            && (this.getPublishTimestamp() == null ? other.getPublishTimestamp() == null : this.getPublishTimestamp().equals(other.getPublishTimestamp()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table berita
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIdOrganisasi() == null) ? 0 : getIdOrganisasi().hashCode());
        result = prime * result + ((getJudul() == null) ? 0 : getJudul().hashCode());
        result = prime * result + ((getIsi() == null) ? 0 : getIsi().hashCode());
        result = prime * result + ((getIsPublished() == null) ? 0 : getIsPublished().hashCode());
        result = prime * result + ((getUserPublish() == null) ? 0 : getUserPublish().hashCode());
        result = prime * result + ((getPublishTimestamp() == null) ? 0 : getPublishTimestamp().hashCode());
        return result;
    }
}