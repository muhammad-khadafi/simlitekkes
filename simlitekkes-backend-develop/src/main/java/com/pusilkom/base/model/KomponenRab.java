package com.pusilkom.base.model;

import java.io.Serializable;

public class KomponenRab implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column komponen_rab.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column komponen_rab.nama
     *
     * @mbg.generated
     */
    private String nama;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column komponen_rab.deskripsi
     *
     * @mbg.generated
     */
    private String deskripsi;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table komponen_rab
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column komponen_rab.id
     *
     * @return the value of komponen_rab.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column komponen_rab.id
     *
     * @param id the value for komponen_rab.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column komponen_rab.nama
     *
     * @return the value of komponen_rab.nama
     *
     * @mbg.generated
     */
    public String getNama() {
        return nama;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column komponen_rab.nama
     *
     * @param nama the value for komponen_rab.nama
     *
     * @mbg.generated
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column komponen_rab.deskripsi
     *
     * @return the value of komponen_rab.deskripsi
     *
     * @mbg.generated
     */
    public String getDeskripsi() {
        return deskripsi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column komponen_rab.deskripsi
     *
     * @param deskripsi the value for komponen_rab.deskripsi
     *
     * @mbg.generated
     */
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table komponen_rab
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
        KomponenRab other = (KomponenRab) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNama() == null ? other.getNama() == null : this.getNama().equals(other.getNama()))
            && (this.getDeskripsi() == null ? other.getDeskripsi() == null : this.getDeskripsi().equals(other.getDeskripsi()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table komponen_rab
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNama() == null) ? 0 : getNama().hashCode());
        result = prime * result + ((getDeskripsi() == null) ? 0 : getDeskripsi().hashCode());
        return result;
    }
}