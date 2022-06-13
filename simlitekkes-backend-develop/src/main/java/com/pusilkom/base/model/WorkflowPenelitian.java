package com.pusilkom.base.model;

import java.io.Serializable;
import java.util.Date;

public class WorkflowPenelitian implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow_penelitian.id_penelitian
     *
     * @mbg.generated
     */
    private Integer idPenelitian;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow_penelitian.id_status_tahap_penelitian
     *
     * @mbg.generated
     */
    private Integer idStatusTahapPenelitian;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow_penelitian.timestamp_update
     *
     * @mbg.generated
     */
    private Date timestampUpdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow_penelitian.is_last
     *
     * @mbg.generated
     */
    private Boolean isLast;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow_penelitian.username
     *
     * @mbg.generated
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table workflow_penelitian
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow_penelitian.id_penelitian
     *
     * @return the value of workflow_penelitian.id_penelitian
     *
     * @mbg.generated
     */
    public Integer getIdPenelitian() {
        return idPenelitian;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow_penelitian.id_penelitian
     *
     * @param idPenelitian the value for workflow_penelitian.id_penelitian
     *
     * @mbg.generated
     */
    public void setIdPenelitian(Integer idPenelitian) {
        this.idPenelitian = idPenelitian;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow_penelitian.id_status_tahap_penelitian
     *
     * @return the value of workflow_penelitian.id_status_tahap_penelitian
     *
     * @mbg.generated
     */
    public Integer getIdStatusTahapPenelitian() {
        return idStatusTahapPenelitian;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow_penelitian.id_status_tahap_penelitian
     *
     * @param idStatusTahapPenelitian the value for workflow_penelitian.id_status_tahap_penelitian
     *
     * @mbg.generated
     */
    public void setIdStatusTahapPenelitian(Integer idStatusTahapPenelitian) {
        this.idStatusTahapPenelitian = idStatusTahapPenelitian;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow_penelitian.timestamp_update
     *
     * @return the value of workflow_penelitian.timestamp_update
     *
     * @mbg.generated
     */
    public Date getTimestampUpdate() {
        return timestampUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow_penelitian.timestamp_update
     *
     * @param timestampUpdate the value for workflow_penelitian.timestamp_update
     *
     * @mbg.generated
     */
    public void setTimestampUpdate(Date timestampUpdate) {
        this.timestampUpdate = timestampUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow_penelitian.is_last
     *
     * @return the value of workflow_penelitian.is_last
     *
     * @mbg.generated
     */
    public Boolean getIsLast() {
        return isLast;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow_penelitian.is_last
     *
     * @param isLast the value for workflow_penelitian.is_last
     *
     * @mbg.generated
     */
    public void setIsLast(Boolean isLast) {
        this.isLast = isLast;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow_penelitian.username
     *
     * @return the value of workflow_penelitian.username
     *
     * @mbg.generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow_penelitian.username
     *
     * @param username the value for workflow_penelitian.username
     *
     * @mbg.generated
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_penelitian
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
        WorkflowPenelitian other = (WorkflowPenelitian) that;
        return (this.getIdPenelitian() == null ? other.getIdPenelitian() == null : this.getIdPenelitian().equals(other.getIdPenelitian()))
            && (this.getIdStatusTahapPenelitian() == null ? other.getIdStatusTahapPenelitian() == null : this.getIdStatusTahapPenelitian().equals(other.getIdStatusTahapPenelitian()))
            && (this.getTimestampUpdate() == null ? other.getTimestampUpdate() == null : this.getTimestampUpdate().equals(other.getTimestampUpdate()))
            && (this.getIsLast() == null ? other.getIsLast() == null : this.getIsLast().equals(other.getIsLast()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_penelitian
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdPenelitian() == null) ? 0 : getIdPenelitian().hashCode());
        result = prime * result + ((getIdStatusTahapPenelitian() == null) ? 0 : getIdStatusTahapPenelitian().hashCode());
        result = prime * result + ((getTimestampUpdate() == null) ? 0 : getTimestampUpdate().hashCode());
        result = prime * result + ((getIsLast() == null) ? 0 : getIsLast().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        return result;
    }
}