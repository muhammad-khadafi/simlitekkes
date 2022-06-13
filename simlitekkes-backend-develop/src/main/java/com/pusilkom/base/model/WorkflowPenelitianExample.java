package com.pusilkom.base.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkflowPenelitianExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table workflow_penelitian
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table workflow_penelitian
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table workflow_penelitian
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_penelitian
     *
     * @mbg.generated
     */
    public WorkflowPenelitianExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_penelitian
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_penelitian
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_penelitian
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_penelitian
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_penelitian
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_penelitian
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_penelitian
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
     * This method corresponds to the database table workflow_penelitian
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
     * This method corresponds to the database table workflow_penelitian
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow_penelitian
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
     * This class corresponds to the database table workflow_penelitian
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

        public Criteria andIdPenelitianIsNull() {
            addCriterion("id_penelitian is null");
            return (Criteria) this;
        }

        public Criteria andIdPenelitianIsNotNull() {
            addCriterion("id_penelitian is not null");
            return (Criteria) this;
        }

        public Criteria andIdPenelitianEqualTo(Integer value) {
            addCriterion("id_penelitian =", value, "idPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdPenelitianNotEqualTo(Integer value) {
            addCriterion("id_penelitian <>", value, "idPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdPenelitianGreaterThan(Integer value) {
            addCriterion("id_penelitian >", value, "idPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdPenelitianGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_penelitian >=", value, "idPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdPenelitianLessThan(Integer value) {
            addCriterion("id_penelitian <", value, "idPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdPenelitianLessThanOrEqualTo(Integer value) {
            addCriterion("id_penelitian <=", value, "idPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdPenelitianIn(List<Integer> values) {
            addCriterion("id_penelitian in", values, "idPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdPenelitianNotIn(List<Integer> values) {
            addCriterion("id_penelitian not in", values, "idPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdPenelitianBetween(Integer value1, Integer value2) {
            addCriterion("id_penelitian between", value1, value2, "idPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdPenelitianNotBetween(Integer value1, Integer value2) {
            addCriterion("id_penelitian not between", value1, value2, "idPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdStatusTahapPenelitianIsNull() {
            addCriterion("id_status_tahap_penelitian is null");
            return (Criteria) this;
        }

        public Criteria andIdStatusTahapPenelitianIsNotNull() {
            addCriterion("id_status_tahap_penelitian is not null");
            return (Criteria) this;
        }

        public Criteria andIdStatusTahapPenelitianEqualTo(Integer value) {
            addCriterion("id_status_tahap_penelitian =", value, "idStatusTahapPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdStatusTahapPenelitianNotEqualTo(Integer value) {
            addCriterion("id_status_tahap_penelitian <>", value, "idStatusTahapPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdStatusTahapPenelitianGreaterThan(Integer value) {
            addCriterion("id_status_tahap_penelitian >", value, "idStatusTahapPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdStatusTahapPenelitianGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_status_tahap_penelitian >=", value, "idStatusTahapPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdStatusTahapPenelitianLessThan(Integer value) {
            addCriterion("id_status_tahap_penelitian <", value, "idStatusTahapPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdStatusTahapPenelitianLessThanOrEqualTo(Integer value) {
            addCriterion("id_status_tahap_penelitian <=", value, "idStatusTahapPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdStatusTahapPenelitianIn(List<Integer> values) {
            addCriterion("id_status_tahap_penelitian in", values, "idStatusTahapPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdStatusTahapPenelitianNotIn(List<Integer> values) {
            addCriterion("id_status_tahap_penelitian not in", values, "idStatusTahapPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdStatusTahapPenelitianBetween(Integer value1, Integer value2) {
            addCriterion("id_status_tahap_penelitian between", value1, value2, "idStatusTahapPenelitian");
            return (Criteria) this;
        }

        public Criteria andIdStatusTahapPenelitianNotBetween(Integer value1, Integer value2) {
            addCriterion("id_status_tahap_penelitian not between", value1, value2, "idStatusTahapPenelitian");
            return (Criteria) this;
        }

        public Criteria andTimestampUpdateIsNull() {
            addCriterion("timestamp_update is null");
            return (Criteria) this;
        }

        public Criteria andTimestampUpdateIsNotNull() {
            addCriterion("timestamp_update is not null");
            return (Criteria) this;
        }

        public Criteria andTimestampUpdateEqualTo(Date value) {
            addCriterion("timestamp_update =", value, "timestampUpdate");
            return (Criteria) this;
        }

        public Criteria andTimestampUpdateNotEqualTo(Date value) {
            addCriterion("timestamp_update <>", value, "timestampUpdate");
            return (Criteria) this;
        }

        public Criteria andTimestampUpdateGreaterThan(Date value) {
            addCriterion("timestamp_update >", value, "timestampUpdate");
            return (Criteria) this;
        }

        public Criteria andTimestampUpdateGreaterThanOrEqualTo(Date value) {
            addCriterion("timestamp_update >=", value, "timestampUpdate");
            return (Criteria) this;
        }

        public Criteria andTimestampUpdateLessThan(Date value) {
            addCriterion("timestamp_update <", value, "timestampUpdate");
            return (Criteria) this;
        }

        public Criteria andTimestampUpdateLessThanOrEqualTo(Date value) {
            addCriterion("timestamp_update <=", value, "timestampUpdate");
            return (Criteria) this;
        }

        public Criteria andTimestampUpdateIn(List<Date> values) {
            addCriterion("timestamp_update in", values, "timestampUpdate");
            return (Criteria) this;
        }

        public Criteria andTimestampUpdateNotIn(List<Date> values) {
            addCriterion("timestamp_update not in", values, "timestampUpdate");
            return (Criteria) this;
        }

        public Criteria andTimestampUpdateBetween(Date value1, Date value2) {
            addCriterion("timestamp_update between", value1, value2, "timestampUpdate");
            return (Criteria) this;
        }

        public Criteria andTimestampUpdateNotBetween(Date value1, Date value2) {
            addCriterion("timestamp_update not between", value1, value2, "timestampUpdate");
            return (Criteria) this;
        }

        public Criteria andIsLastIsNull() {
            addCriterion("is_last is null");
            return (Criteria) this;
        }

        public Criteria andIsLastIsNotNull() {
            addCriterion("is_last is not null");
            return (Criteria) this;
        }

        public Criteria andIsLastEqualTo(Boolean value) {
            addCriterion("is_last =", value, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastNotEqualTo(Boolean value) {
            addCriterion("is_last <>", value, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastGreaterThan(Boolean value) {
            addCriterion("is_last >", value, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_last >=", value, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastLessThan(Boolean value) {
            addCriterion("is_last <", value, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastLessThanOrEqualTo(Boolean value) {
            addCriterion("is_last <=", value, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastIn(List<Boolean> values) {
            addCriterion("is_last in", values, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastNotIn(List<Boolean> values) {
            addCriterion("is_last not in", values, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastBetween(Boolean value1, Boolean value2) {
            addCriterion("is_last between", value1, value2, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_last not between", value1, value2, "isLast");
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

        public Criteria andUsernameLikeInsensitive(String value) {
            addCriterion("upper(username) like", value.toUpperCase(), "username");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table workflow_penelitian
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
     * This class corresponds to the database table workflow_penelitian
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