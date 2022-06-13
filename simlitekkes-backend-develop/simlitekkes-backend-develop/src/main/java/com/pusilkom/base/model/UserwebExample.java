package com.pusilkom.base.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserwebExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table userweb
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table userweb
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table userweb
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb
     *
     * @mbg.generated
     */
    public UserwebExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table userweb
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andLastLoginIsNull() {
            addCriterion("last_login is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginIsNotNull() {
            addCriterion("last_login is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginEqualTo(Date value) {
            addCriterion("last_login =", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginNotEqualTo(Date value) {
            addCriterion("last_login <>", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginGreaterThan(Date value) {
            addCriterion("last_login >", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginGreaterThanOrEqualTo(Date value) {
            addCriterion("last_login >=", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginLessThan(Date value) {
            addCriterion("last_login <", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginLessThanOrEqualTo(Date value) {
            addCriterion("last_login <=", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginIn(List<Date> values) {
            addCriterion("last_login in", values, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginNotIn(List<Date> values) {
            addCriterion("last_login not in", values, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginBetween(Date value1, Date value2) {
            addCriterion("last_login between", value1, value2, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginNotBetween(Date value1, Date value2) {
            addCriterion("last_login not between", value1, value2, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andFotoIsNull() {
            addCriterion("foto is null");
            return (Criteria) this;
        }

        public Criteria andFotoIsNotNull() {
            addCriterion("foto is not null");
            return (Criteria) this;
        }

        public Criteria andFotoEqualTo(String value) {
            addCriterion("foto =", value, "foto");
            return (Criteria) this;
        }

        public Criteria andFotoNotEqualTo(String value) {
            addCriterion("foto <>", value, "foto");
            return (Criteria) this;
        }

        public Criteria andFotoGreaterThan(String value) {
            addCriterion("foto >", value, "foto");
            return (Criteria) this;
        }

        public Criteria andFotoGreaterThanOrEqualTo(String value) {
            addCriterion("foto >=", value, "foto");
            return (Criteria) this;
        }

        public Criteria andFotoLessThan(String value) {
            addCriterion("foto <", value, "foto");
            return (Criteria) this;
        }

        public Criteria andFotoLessThanOrEqualTo(String value) {
            addCriterion("foto <=", value, "foto");
            return (Criteria) this;
        }

        public Criteria andFotoLike(String value) {
            addCriterion("foto like", value, "foto");
            return (Criteria) this;
        }

        public Criteria andFotoNotLike(String value) {
            addCriterion("foto not like", value, "foto");
            return (Criteria) this;
        }

        public Criteria andFotoIn(List<String> values) {
            addCriterion("foto in", values, "foto");
            return (Criteria) this;
        }

        public Criteria andFotoNotIn(List<String> values) {
            addCriterion("foto not in", values, "foto");
            return (Criteria) this;
        }

        public Criteria andFotoBetween(String value1, String value2) {
            addCriterion("foto between", value1, value2, "foto");
            return (Criteria) this;
        }

        public Criteria andFotoNotBetween(String value1, String value2) {
            addCriterion("foto not between", value1, value2, "foto");
            return (Criteria) this;
        }

        public Criteria andNipIsNull() {
            addCriterion("nip is null");
            return (Criteria) this;
        }

        public Criteria andNipIsNotNull() {
            addCriterion("nip is not null");
            return (Criteria) this;
        }

        public Criteria andNipEqualTo(String value) {
            addCriterion("nip =", value, "nip");
            return (Criteria) this;
        }

        public Criteria andNipNotEqualTo(String value) {
            addCriterion("nip <>", value, "nip");
            return (Criteria) this;
        }

        public Criteria andNipGreaterThan(String value) {
            addCriterion("nip >", value, "nip");
            return (Criteria) this;
        }

        public Criteria andNipGreaterThanOrEqualTo(String value) {
            addCriterion("nip >=", value, "nip");
            return (Criteria) this;
        }

        public Criteria andNipLessThan(String value) {
            addCriterion("nip <", value, "nip");
            return (Criteria) this;
        }

        public Criteria andNipLessThanOrEqualTo(String value) {
            addCriterion("nip <=", value, "nip");
            return (Criteria) this;
        }

        public Criteria andNipLike(String value) {
            addCriterion("nip like", value, "nip");
            return (Criteria) this;
        }

        public Criteria andNipNotLike(String value) {
            addCriterion("nip not like", value, "nip");
            return (Criteria) this;
        }

        public Criteria andNipIn(List<String> values) {
            addCriterion("nip in", values, "nip");
            return (Criteria) this;
        }

        public Criteria andNipNotIn(List<String> values) {
            addCriterion("nip not in", values, "nip");
            return (Criteria) this;
        }

        public Criteria andNipBetween(String value1, String value2) {
            addCriterion("nip between", value1, value2, "nip");
            return (Criteria) this;
        }

        public Criteria andNipNotBetween(String value1, String value2) {
            addCriterion("nip not between", value1, value2, "nip");
            return (Criteria) this;
        }

        public Criteria andNamaIsNull() {
            addCriterion("nama is null");
            return (Criteria) this;
        }

        public Criteria andNamaIsNotNull() {
            addCriterion("nama is not null");
            return (Criteria) this;
        }

        public Criteria andNamaEqualTo(String value) {
            addCriterion("nama =", value, "nama");
            return (Criteria) this;
        }

        public Criteria andNamaNotEqualTo(String value) {
            addCriterion("nama <>", value, "nama");
            return (Criteria) this;
        }

        public Criteria andNamaGreaterThan(String value) {
            addCriterion("nama >", value, "nama");
            return (Criteria) this;
        }

        public Criteria andNamaGreaterThanOrEqualTo(String value) {
            addCriterion("nama >=", value, "nama");
            return (Criteria) this;
        }

        public Criteria andNamaLessThan(String value) {
            addCriterion("nama <", value, "nama");
            return (Criteria) this;
        }

        public Criteria andNamaLessThanOrEqualTo(String value) {
            addCriterion("nama <=", value, "nama");
            return (Criteria) this;
        }

        public Criteria andNamaLike(String value) {
            addCriterion("nama like", value, "nama");
            return (Criteria) this;
        }

        public Criteria andNamaNotLike(String value) {
            addCriterion("nama not like", value, "nama");
            return (Criteria) this;
        }

        public Criteria andNamaIn(List<String> values) {
            addCriterion("nama in", values, "nama");
            return (Criteria) this;
        }

        public Criteria andNamaNotIn(List<String> values) {
            addCriterion("nama not in", values, "nama");
            return (Criteria) this;
        }

        public Criteria andNamaBetween(String value1, String value2) {
            addCriterion("nama between", value1, value2, "nama");
            return (Criteria) this;
        }

        public Criteria andNamaNotBetween(String value1, String value2) {
            addCriterion("nama not between", value1, value2, "nama");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andIsActiveIsNull() {
            addCriterion("is_active is null");
            return (Criteria) this;
        }

        public Criteria andIsActiveIsNotNull() {
            addCriterion("is_active is not null");
            return (Criteria) this;
        }

        public Criteria andIsActiveEqualTo(Boolean value) {
            addCriterion("is_active =", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotEqualTo(Boolean value) {
            addCriterion("is_active <>", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThan(Boolean value) {
            addCriterion("is_active >", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_active >=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThan(Boolean value) {
            addCriterion("is_active <", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThanOrEqualTo(Boolean value) {
            addCriterion("is_active <=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveIn(List<Boolean> values) {
            addCriterion("is_active in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotIn(List<Boolean> values) {
            addCriterion("is_active not in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveBetween(Boolean value1, Boolean value2) {
            addCriterion("is_active between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_active not between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andIdOrganisasiIsNull() {
            addCriterion("id_organisasi is null");
            return (Criteria) this;
        }

        public Criteria andIdOrganisasiIsNotNull() {
            addCriterion("id_organisasi is not null");
            return (Criteria) this;
        }

        public Criteria andIdOrganisasiEqualTo(Integer value) {
            addCriterion("id_organisasi =", value, "idOrganisasi");
            return (Criteria) this;
        }

        public Criteria andIdOrganisasiNotEqualTo(Integer value) {
            addCriterion("id_organisasi <>", value, "idOrganisasi");
            return (Criteria) this;
        }

        public Criteria andIdOrganisasiGreaterThan(Integer value) {
            addCriterion("id_organisasi >", value, "idOrganisasi");
            return (Criteria) this;
        }

        public Criteria andIdOrganisasiGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_organisasi >=", value, "idOrganisasi");
            return (Criteria) this;
        }

        public Criteria andIdOrganisasiLessThan(Integer value) {
            addCriterion("id_organisasi <", value, "idOrganisasi");
            return (Criteria) this;
        }

        public Criteria andIdOrganisasiLessThanOrEqualTo(Integer value) {
            addCriterion("id_organisasi <=", value, "idOrganisasi");
            return (Criteria) this;
        }

        public Criteria andIdOrganisasiIn(List<Integer> values) {
            addCriterion("id_organisasi in", values, "idOrganisasi");
            return (Criteria) this;
        }

        public Criteria andIdOrganisasiNotIn(List<Integer> values) {
            addCriterion("id_organisasi not in", values, "idOrganisasi");
            return (Criteria) this;
        }

        public Criteria andIdOrganisasiBetween(Integer value1, Integer value2) {
            addCriterion("id_organisasi between", value1, value2, "idOrganisasi");
            return (Criteria) this;
        }

        public Criteria andIdOrganisasiNotBetween(Integer value1, Integer value2) {
            addCriterion("id_organisasi not between", value1, value2, "idOrganisasi");
            return (Criteria) this;
        }

        public Criteria andFotoNameIsNull() {
            addCriterion("foto_name is null");
            return (Criteria) this;
        }

        public Criteria andFotoNameIsNotNull() {
            addCriterion("foto_name is not null");
            return (Criteria) this;
        }

        public Criteria andFotoNameEqualTo(String value) {
            addCriterion("foto_name =", value, "fotoName");
            return (Criteria) this;
        }

        public Criteria andFotoNameNotEqualTo(String value) {
            addCriterion("foto_name <>", value, "fotoName");
            return (Criteria) this;
        }

        public Criteria andFotoNameGreaterThan(String value) {
            addCriterion("foto_name >", value, "fotoName");
            return (Criteria) this;
        }

        public Criteria andFotoNameGreaterThanOrEqualTo(String value) {
            addCriterion("foto_name >=", value, "fotoName");
            return (Criteria) this;
        }

        public Criteria andFotoNameLessThan(String value) {
            addCriterion("foto_name <", value, "fotoName");
            return (Criteria) this;
        }

        public Criteria andFotoNameLessThanOrEqualTo(String value) {
            addCriterion("foto_name <=", value, "fotoName");
            return (Criteria) this;
        }

        public Criteria andFotoNameLike(String value) {
            addCriterion("foto_name like", value, "fotoName");
            return (Criteria) this;
        }

        public Criteria andFotoNameNotLike(String value) {
            addCriterion("foto_name not like", value, "fotoName");
            return (Criteria) this;
        }

        public Criteria andFotoNameIn(List<String> values) {
            addCriterion("foto_name in", values, "fotoName");
            return (Criteria) this;
        }

        public Criteria andFotoNameNotIn(List<String> values) {
            addCriterion("foto_name not in", values, "fotoName");
            return (Criteria) this;
        }

        public Criteria andFotoNameBetween(String value1, String value2) {
            addCriterion("foto_name between", value1, value2, "fotoName");
            return (Criteria) this;
        }

        public Criteria andFotoNameNotBetween(String value1, String value2) {
            addCriterion("foto_name not between", value1, value2, "fotoName");
            return (Criteria) this;
        }

        public Criteria andNitkIsNull() {
            addCriterion("nitk is null");
            return (Criteria) this;
        }

        public Criteria andNitkIsNotNull() {
            addCriterion("nitk is not null");
            return (Criteria) this;
        }

        public Criteria andNitkEqualTo(String value) {
            addCriterion("nitk =", value, "nitk");
            return (Criteria) this;
        }

        public Criteria andNitkNotEqualTo(String value) {
            addCriterion("nitk <>", value, "nitk");
            return (Criteria) this;
        }

        public Criteria andNitkGreaterThan(String value) {
            addCriterion("nitk >", value, "nitk");
            return (Criteria) this;
        }

        public Criteria andNitkGreaterThanOrEqualTo(String value) {
            addCriterion("nitk >=", value, "nitk");
            return (Criteria) this;
        }

        public Criteria andNitkLessThan(String value) {
            addCriterion("nitk <", value, "nitk");
            return (Criteria) this;
        }

        public Criteria andNitkLessThanOrEqualTo(String value) {
            addCriterion("nitk <=", value, "nitk");
            return (Criteria) this;
        }

        public Criteria andNitkLike(String value) {
            addCriterion("nitk like", value, "nitk");
            return (Criteria) this;
        }

        public Criteria andNitkNotLike(String value) {
            addCriterion("nitk not like", value, "nitk");
            return (Criteria) this;
        }

        public Criteria andNitkIn(List<String> values) {
            addCriterion("nitk in", values, "nitk");
            return (Criteria) this;
        }

        public Criteria andNitkNotIn(List<String> values) {
            addCriterion("nitk not in", values, "nitk");
            return (Criteria) this;
        }

        public Criteria andNitkBetween(String value1, String value2) {
            addCriterion("nitk between", value1, value2, "nitk");
            return (Criteria) this;
        }

        public Criteria andNitkNotBetween(String value1, String value2) {
            addCriterion("nitk not between", value1, value2, "nitk");
            return (Criteria) this;
        }

        public Criteria andNidnIsNull() {
            addCriterion("nidn is null");
            return (Criteria) this;
        }

        public Criteria andNidnIsNotNull() {
            addCriterion("nidn is not null");
            return (Criteria) this;
        }

        public Criteria andNidnEqualTo(String value) {
            addCriterion("nidn =", value, "nidn");
            return (Criteria) this;
        }

        public Criteria andNidnNotEqualTo(String value) {
            addCriterion("nidn <>", value, "nidn");
            return (Criteria) this;
        }

        public Criteria andNidnGreaterThan(String value) {
            addCriterion("nidn >", value, "nidn");
            return (Criteria) this;
        }

        public Criteria andNidnGreaterThanOrEqualTo(String value) {
            addCriterion("nidn >=", value, "nidn");
            return (Criteria) this;
        }

        public Criteria andNidnLessThan(String value) {
            addCriterion("nidn <", value, "nidn");
            return (Criteria) this;
        }

        public Criteria andNidnLessThanOrEqualTo(String value) {
            addCriterion("nidn <=", value, "nidn");
            return (Criteria) this;
        }

        public Criteria andNidnLike(String value) {
            addCriterion("nidn like", value, "nidn");
            return (Criteria) this;
        }

        public Criteria andNidnNotLike(String value) {
            addCriterion("nidn not like", value, "nidn");
            return (Criteria) this;
        }

        public Criteria andNidnIn(List<String> values) {
            addCriterion("nidn in", values, "nidn");
            return (Criteria) this;
        }

        public Criteria andNidnNotIn(List<String> values) {
            addCriterion("nidn not in", values, "nidn");
            return (Criteria) this;
        }

        public Criteria andNidnBetween(String value1, String value2) {
            addCriterion("nidn between", value1, value2, "nidn");
            return (Criteria) this;
        }

        public Criteria andNidnNotBetween(String value1, String value2) {
            addCriterion("nidn not between", value1, value2, "nidn");
            return (Criteria) this;
        }

        public Criteria andUsernameLikeInsensitive(String value) {
            addCriterion("upper(username) like", value.toUpperCase(), "username");
            return (Criteria) this;
        }

        public Criteria andPasswordLikeInsensitive(String value) {
            addCriterion("upper(password) like", value.toUpperCase(), "password");
            return (Criteria) this;
        }

        public Criteria andFotoLikeInsensitive(String value) {
            addCriterion("upper(foto) like", value.toUpperCase(), "foto");
            return (Criteria) this;
        }

        public Criteria andNipLikeInsensitive(String value) {
            addCriterion("upper(nip) like", value.toUpperCase(), "nip");
            return (Criteria) this;
        }

        public Criteria andNamaLikeInsensitive(String value) {
            addCriterion("upper(nama) like", value.toUpperCase(), "nama");
            return (Criteria) this;
        }

        public Criteria andEmailLikeInsensitive(String value) {
            addCriterion("upper(email) like", value.toUpperCase(), "email");
            return (Criteria) this;
        }

        public Criteria andFotoNameLikeInsensitive(String value) {
            addCriterion("upper(foto_name) like", value.toUpperCase(), "fotoName");
            return (Criteria) this;
        }

        public Criteria andNitkLikeInsensitive(String value) {
            addCriterion("upper(nitk) like", value.toUpperCase(), "nitk");
            return (Criteria) this;
        }

        public Criteria andNidnLikeInsensitive(String value) {
            addCriterion("upper(nidn) like", value.toUpperCase(), "nidn");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table userweb
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table userweb
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}