package com.pusilkom.base.model;

import java.io.Serializable;

public class RumpunIlmu implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rumpun_ilmu.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rumpun_ilmu.id_parent_rumpun_ilmu
     *
     * @mbg.generated
     */
    private Integer idParentRumpunIlmu;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rumpun_ilmu.kode
     *
     * @mbg.generated
     */
    private String kode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rumpun_ilmu.nama
     *
     * @mbg.generated
     */
    private String nama;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column rumpun_ilmu.level
     *
     * @mbg.generated
     */
    private Short level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rumpun_ilmu
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rumpun_ilmu.id
     *
     * @return the value of rumpun_ilmu.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rumpun_ilmu.id
     *
     * @param id the value for rumpun_ilmu.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rumpun_ilmu.id_parent_rumpun_ilmu
     *
     * @return the value of rumpun_ilmu.id_parent_rumpun_ilmu
     *
     * @mbg.generated
     */
    public Integer getIdParentRumpunIlmu() {
        return idParentRumpunIlmu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rumpun_ilmu.id_parent_rumpun_ilmu
     *
     * @param idParentRumpunIlmu the value for rumpun_ilmu.id_parent_rumpun_ilmu
     *
     * @mbg.generated
     */
    public void setIdParentRumpunIlmu(Integer idParentRumpunIlmu) {
        this.idParentRumpunIlmu = idParentRumpunIlmu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rumpun_ilmu.kode
     *
     * @return the value of rumpun_ilmu.kode
     *
     * @mbg.generated
     */
    public String getKode() {
        return kode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rumpun_ilmu.kode
     *
     * @param kode the value for rumpun_ilmu.kode
     *
     * @mbg.generated
     */
    public void setKode(String kode) {
        this.kode = kode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rumpun_ilmu.nama
     *
     * @return the value of rumpun_ilmu.nama
     *
     * @mbg.generated
     */
    public String getNama() {
        return nama;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rumpun_ilmu.nama
     *
     * @param nama the value for rumpun_ilmu.nama
     *
     * @mbg.generated
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column rumpun_ilmu.level
     *
     * @return the value of rumpun_ilmu.level
     *
     * @mbg.generated
     */
    public Short getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column rumpun_ilmu.level
     *
     * @param level the value for rumpun_ilmu.level
     *
     * @mbg.generated
     */
    public void setLevel(Short level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rumpun_ilmu
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
        RumpunIlmu other = (RumpunIlmu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIdParentRumpunIlmu() == null ? other.getIdParentRumpunIlmu() == null : this.getIdParentRumpunIlmu().equals(other.getIdParentRumpunIlmu()))
            && (this.getKode() == null ? other.getKode() == null : this.getKode().equals(other.getKode()))
            && (this.getNama() == null ? other.getNama() == null : this.getNama().equals(other.getNama()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rumpun_ilmu
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIdParentRumpunIlmu() == null) ? 0 : getIdParentRumpunIlmu().hashCode());
        result = prime * result + ((getKode() == null) ? 0 : getKode().hashCode());
        result = prime * result + ((getNama() == null) ? 0 : getNama().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        return result;
    }
}