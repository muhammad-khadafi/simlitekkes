package com.pusilkom.base.model;

import java.util.ArrayList;
import java.util.List;

public class UserwebHasRoleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table userweb_has_role
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table userweb_has_role
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table userweb_has_role
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb_has_role
     *
     * @mbg.generated
     */
    public UserwebHasRoleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb_has_role
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb_has_role
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb_has_role
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb_has_role
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb_has_role
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb_has_role
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb_has_role
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
     * This method corresponds to the database table userweb_has_role
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
     * This method corresponds to the database table userweb_has_role
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userweb_has_role
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
     * This class corresponds to the database table userweb_has_role
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdUserwebIsNull() {
            addCriterion("id_userweb is null");
            return (Criteria) this;
        }

        public Criteria andIdUserwebIsNotNull() {
            addCriterion("id_userweb is not null");
            return (Criteria) this;
        }

        public Criteria andIdUserwebEqualTo(Long value) {
            addCriterion("id_userweb =", value, "idUserweb");
            return (Criteria) this;
        }

        public Criteria andIdUserwebNotEqualTo(Long value) {
            addCriterion("id_userweb <>", value, "idUserweb");
            return (Criteria) this;
        }

        public Criteria andIdUserwebGreaterThan(Long value) {
            addCriterion("id_userweb >", value, "idUserweb");
            return (Criteria) this;
        }

        public Criteria andIdUserwebGreaterThanOrEqualTo(Long value) {
            addCriterion("id_userweb >=", value, "idUserweb");
            return (Criteria) this;
        }

        public Criteria andIdUserwebLessThan(Long value) {
            addCriterion("id_userweb <", value, "idUserweb");
            return (Criteria) this;
        }

        public Criteria andIdUserwebLessThanOrEqualTo(Long value) {
            addCriterion("id_userweb <=", value, "idUserweb");
            return (Criteria) this;
        }

        public Criteria andIdUserwebIn(List<Long> values) {
            addCriterion("id_userweb in", values, "idUserweb");
            return (Criteria) this;
        }

        public Criteria andIdUserwebNotIn(List<Long> values) {
            addCriterion("id_userweb not in", values, "idUserweb");
            return (Criteria) this;
        }

        public Criteria andIdUserwebBetween(Long value1, Long value2) {
            addCriterion("id_userweb between", value1, value2, "idUserweb");
            return (Criteria) this;
        }

        public Criteria andIdUserwebNotBetween(Long value1, Long value2) {
            addCriterion("id_userweb not between", value1, value2, "idUserweb");
            return (Criteria) this;
        }

        public Criteria andIdRoleIsNull() {
            addCriterion("id_role is null");
            return (Criteria) this;
        }

        public Criteria andIdRoleIsNotNull() {
            addCriterion("id_role is not null");
            return (Criteria) this;
        }

        public Criteria andIdRoleEqualTo(Long value) {
            addCriterion("id_role =", value, "idRole");
            return (Criteria) this;
        }

        public Criteria andIdRoleNotEqualTo(Long value) {
            addCriterion("id_role <>", value, "idRole");
            return (Criteria) this;
        }

        public Criteria andIdRoleGreaterThan(Long value) {
            addCriterion("id_role >", value, "idRole");
            return (Criteria) this;
        }

        public Criteria andIdRoleGreaterThanOrEqualTo(Long value) {
            addCriterion("id_role >=", value, "idRole");
            return (Criteria) this;
        }

        public Criteria andIdRoleLessThan(Long value) {
            addCriterion("id_role <", value, "idRole");
            return (Criteria) this;
        }

        public Criteria andIdRoleLessThanOrEqualTo(Long value) {
            addCriterion("id_role <=", value, "idRole");
            return (Criteria) this;
        }

        public Criteria andIdRoleIn(List<Long> values) {
            addCriterion("id_role in", values, "idRole");
            return (Criteria) this;
        }

        public Criteria andIdRoleNotIn(List<Long> values) {
            addCriterion("id_role not in", values, "idRole");
            return (Criteria) this;
        }

        public Criteria andIdRoleBetween(Long value1, Long value2) {
            addCriterion("id_role between", value1, value2, "idRole");
            return (Criteria) this;
        }

        public Criteria andIdRoleNotBetween(Long value1, Long value2) {
            addCriterion("id_role not between", value1, value2, "idRole");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table userweb_has_role
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
     * This class corresponds to the database table userweb_has_role
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