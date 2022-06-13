package com.pusilkom.base.model;

import java.io.Serializable;

public class UserwebHasRole implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userweb_has_role.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userweb_has_role.id_userweb
     *
     * @mbg.generated
     */
    private Long idUserweb;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userweb_has_role.id_role
     *
     * @mbg.generated
     */
    private Long idRole;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table userweb_has_role
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userweb_has_role.id
     *
     * @return the value of userweb_has_role.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userweb_has_role.id
     *
     * @param id the value for userweb_has_role.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userweb_has_role.id_userweb
     *
     * @return the value of userweb_has_role.id_userweb
     *
     * @mbg.generated
     */
    public Long getIdUserweb() {
        return idUserweb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userweb_has_role.id_userweb
     *
     * @param idUserweb the value for userweb_has_role.id_userweb
     *
     * @mbg.generated
     */
    public void setIdUserweb(Long idUserweb) {
        this.idUserweb = idUserweb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userweb_has_role.id_role
     *
     * @return the value of userweb_has_role.id_role
     *
     * @mbg.generated
     */
    public Long getIdRole() {
        return idRole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userweb_has_role.id_role
     *
     * @param idRole the value for userweb_has_role.id_role
     *
     * @mbg.generated
     */
    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb_has_role
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
        UserwebHasRole other = (UserwebHasRole) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIdUserweb() == null ? other.getIdUserweb() == null : this.getIdUserweb().equals(other.getIdUserweb()))
            && (this.getIdRole() == null ? other.getIdRole() == null : this.getIdRole().equals(other.getIdRole()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb_has_role
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIdUserweb() == null) ? 0 : getIdUserweb().hashCode());
        result = prime * result + ((getIdRole() == null) ? 0 : getIdRole().hashCode());
        return result;
    }

}