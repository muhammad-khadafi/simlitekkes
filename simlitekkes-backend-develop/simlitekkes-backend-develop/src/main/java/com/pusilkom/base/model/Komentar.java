package com.pusilkom.base.model;

import java.io.Serializable;
import java.util.Date;

public class Komentar implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column komentar.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column komentar.id_penelitian
     *
     * @mbg.generated
     */
    private Integer idPenelitian;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column komentar.id_bagian_usulan
     *
     * @mbg.generated
     */
    private Integer idBagianUsulan;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column komentar.komentar
     *
     * @mbg.generated
     */
    private String komentar;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column komentar.timestamp_update
     *
     * @mbg.generated
     */
    private Date timestampUpdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column komentar.id_user
     *
     * @mbg.generated
     */
    private Integer idUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table komentar
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column komentar.id
     *
     * @return the value of komentar.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column komentar.id
     *
     * @param id the value for komentar.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column komentar.id_penelitian
     *
     * @return the value of komentar.id_penelitian
     *
     * @mbg.generated
     */
    public Integer getIdPenelitian() {
        return idPenelitian;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column komentar.id_penelitian
     *
     * @param idPenelitian the value for komentar.id_penelitian
     *
     * @mbg.generated
     */
    public void setIdPenelitian(Integer idPenelitian) {
        this.idPenelitian = idPenelitian;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column komentar.id_bagian_usulan
     *
     * @return the value of komentar.id_bagian_usulan
     *
     * @mbg.generated
     */
    public Integer getIdBagianUsulan() {
        return idBagianUsulan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column komentar.id_bagian_usulan
     *
     * @param idBagianUsulan the value for komentar.id_bagian_usulan
     *
     * @mbg.generated
     */
    public void setIdBagianUsulan(Integer idBagianUsulan) {
        this.idBagianUsulan = idBagianUsulan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column komentar.komentar
     *
     * @return the value of komentar.komentar
     *
     * @mbg.generated
     */
    public String getKomentar() {
        return komentar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column komentar.komentar
     *
     * @param komentar the value for komentar.komentar
     *
     * @mbg.generated
     */
    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column komentar.timestamp_update
     *
     * @return the value of komentar.timestamp_update
     *
     * @mbg.generated
     */
    public Date getTimestampUpdate() {
        return timestampUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column komentar.timestamp_update
     *
     * @param timestampUpdate the value for komentar.timestamp_update
     *
     * @mbg.generated
     */
    public void setTimestampUpdate(Date timestampUpdate) {
        this.timestampUpdate = timestampUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column komentar.id_user
     *
     * @return the value of komentar.id_user
     *
     * @mbg.generated
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column komentar.id_user
     *
     * @param idUser the value for komentar.id_user
     *
     * @mbg.generated
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table komentar
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
        Komentar other = (Komentar) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIdPenelitian() == null ? other.getIdPenelitian() == null : this.getIdPenelitian().equals(other.getIdPenelitian()))
            && (this.getIdBagianUsulan() == null ? other.getIdBagianUsulan() == null : this.getIdBagianUsulan().equals(other.getIdBagianUsulan()))
            && (this.getKomentar() == null ? other.getKomentar() == null : this.getKomentar().equals(other.getKomentar()))
            && (this.getTimestampUpdate() == null ? other.getTimestampUpdate() == null : this.getTimestampUpdate().equals(other.getTimestampUpdate()))
            && (this.getIdUser() == null ? other.getIdUser() == null : this.getIdUser().equals(other.getIdUser()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table komentar
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIdPenelitian() == null) ? 0 : getIdPenelitian().hashCode());
        result = prime * result + ((getIdBagianUsulan() == null) ? 0 : getIdBagianUsulan().hashCode());
        result = prime * result + ((getKomentar() == null) ? 0 : getKomentar().hashCode());
        result = prime * result + ((getTimestampUpdate() == null) ? 0 : getTimestampUpdate().hashCode());
        result = prime * result + ((getIdUser() == null) ? 0 : getIdUser().hashCode());
        return result;
    }
}