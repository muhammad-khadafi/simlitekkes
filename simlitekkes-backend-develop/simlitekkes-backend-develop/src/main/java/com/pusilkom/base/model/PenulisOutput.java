package com.pusilkom.base.model;

import java.io.Serializable;

public class PenulisOutput implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column penulis_output.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column penulis_output.id_peran_penulis_output
     *
     * @mbg.generated
     */
    private Integer idPeranPenulisOutput;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column penulis_output.id_output
     *
     * @mbg.generated
     */
    private Integer idOutput;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column penulis_output.id_peneliti
     *
     * @mbg.generated
     */
    private Integer idPeneliti;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table penulis_output
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column penulis_output.id
     *
     * @return the value of penulis_output.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column penulis_output.id
     *
     * @param id the value for penulis_output.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column penulis_output.id_peran_penulis_output
     *
     * @return the value of penulis_output.id_peran_penulis_output
     *
     * @mbg.generated
     */
    public Integer getIdPeranPenulisOutput() {
        return idPeranPenulisOutput;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column penulis_output.id_peran_penulis_output
     *
     * @param idPeranPenulisOutput the value for penulis_output.id_peran_penulis_output
     *
     * @mbg.generated
     */
    public void setIdPeranPenulisOutput(Integer idPeranPenulisOutput) {
        this.idPeranPenulisOutput = idPeranPenulisOutput;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column penulis_output.id_output
     *
     * @return the value of penulis_output.id_output
     *
     * @mbg.generated
     */
    public Integer getIdOutput() {
        return idOutput;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column penulis_output.id_output
     *
     * @param idOutput the value for penulis_output.id_output
     *
     * @mbg.generated
     */
    public void setIdOutput(Integer idOutput) {
        this.idOutput = idOutput;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column penulis_output.id_peneliti
     *
     * @return the value of penulis_output.id_peneliti
     *
     * @mbg.generated
     */
    public Integer getIdPeneliti() {
        return idPeneliti;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column penulis_output.id_peneliti
     *
     * @param idPeneliti the value for penulis_output.id_peneliti
     *
     * @mbg.generated
     */
    public void setIdPeneliti(Integer idPeneliti) {
        this.idPeneliti = idPeneliti;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table penulis_output
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
        PenulisOutput other = (PenulisOutput) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIdPeranPenulisOutput() == null ? other.getIdPeranPenulisOutput() == null : this.getIdPeranPenulisOutput().equals(other.getIdPeranPenulisOutput()))
            && (this.getIdOutput() == null ? other.getIdOutput() == null : this.getIdOutput().equals(other.getIdOutput()))
            && (this.getIdPeneliti() == null ? other.getIdPeneliti() == null : this.getIdPeneliti().equals(other.getIdPeneliti()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table penulis_output
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIdPeranPenulisOutput() == null) ? 0 : getIdPeranPenulisOutput().hashCode());
        result = prime * result + ((getIdOutput() == null) ? 0 : getIdOutput().hashCode());
        result = prime * result + ((getIdPeneliti() == null) ? 0 : getIdPeneliti().hashCode());
        return result;
    }
}