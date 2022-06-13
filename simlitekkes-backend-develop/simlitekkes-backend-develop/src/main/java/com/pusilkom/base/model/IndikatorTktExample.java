package com.pusilkom.base.model;

import java.util.ArrayList;
import java.util.List;

public class IndikatorTktExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table indikator_tkt
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table indikator_tkt
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table indikator_tkt
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table indikator_tkt
     *
     * @mbg.generated
     */
    public IndikatorTktExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table indikator_tkt
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table indikator_tkt
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table indikator_tkt
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table indikator_tkt
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table indikator_tkt
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table indikator_tkt
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table indikator_tkt
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
     * This method corresponds to the database table indikator_tkt
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
     * This method corresponds to the database table indikator_tkt
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table indikator_tkt
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
     * This class corresponds to the database table indikator_tkt
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

        public Criteria andIdTingkatKesiapanTeknologiIsNull() {
            addCriterion("id_tingkat_kesiapan_teknologi is null");
            return (Criteria) this;
        }

        public Criteria andIdTingkatKesiapanTeknologiIsNotNull() {
            addCriterion("id_tingkat_kesiapan_teknologi is not null");
            return (Criteria) this;
        }

        public Criteria andIdTingkatKesiapanTeknologiEqualTo(Integer value) {
            addCriterion("id_tingkat_kesiapan_teknologi =", value, "idTingkatKesiapanTeknologi");
            return (Criteria) this;
        }

        public Criteria andIdTingkatKesiapanTeknologiNotEqualTo(Integer value) {
            addCriterion("id_tingkat_kesiapan_teknologi <>", value, "idTingkatKesiapanTeknologi");
            return (Criteria) this;
        }

        public Criteria andIdTingkatKesiapanTeknologiGreaterThan(Integer value) {
            addCriterion("id_tingkat_kesiapan_teknologi >", value, "idTingkatKesiapanTeknologi");
            return (Criteria) this;
        }

        public Criteria andIdTingkatKesiapanTeknologiGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_tingkat_kesiapan_teknologi >=", value, "idTingkatKesiapanTeknologi");
            return (Criteria) this;
        }

        public Criteria andIdTingkatKesiapanTeknologiLessThan(Integer value) {
            addCriterion("id_tingkat_kesiapan_teknologi <", value, "idTingkatKesiapanTeknologi");
            return (Criteria) this;
        }

        public Criteria andIdTingkatKesiapanTeknologiLessThanOrEqualTo(Integer value) {
            addCriterion("id_tingkat_kesiapan_teknologi <=", value, "idTingkatKesiapanTeknologi");
            return (Criteria) this;
        }

        public Criteria andIdTingkatKesiapanTeknologiIn(List<Integer> values) {
            addCriterion("id_tingkat_kesiapan_teknologi in", values, "idTingkatKesiapanTeknologi");
            return (Criteria) this;
        }

        public Criteria andIdTingkatKesiapanTeknologiNotIn(List<Integer> values) {
            addCriterion("id_tingkat_kesiapan_teknologi not in", values, "idTingkatKesiapanTeknologi");
            return (Criteria) this;
        }

        public Criteria andIdTingkatKesiapanTeknologiBetween(Integer value1, Integer value2) {
            addCriterion("id_tingkat_kesiapan_teknologi between", value1, value2, "idTingkatKesiapanTeknologi");
            return (Criteria) this;
        }

        public Criteria andIdTingkatKesiapanTeknologiNotBetween(Integer value1, Integer value2) {
            addCriterion("id_tingkat_kesiapan_teknologi not between", value1, value2, "idTingkatKesiapanTeknologi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiIsNull() {
            addCriterion("deskripsi is null");
            return (Criteria) this;
        }

        public Criteria andDeskripsiIsNotNull() {
            addCriterion("deskripsi is not null");
            return (Criteria) this;
        }

        public Criteria andDeskripsiEqualTo(String value) {
            addCriterion("deskripsi =", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiNotEqualTo(String value) {
            addCriterion("deskripsi <>", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiGreaterThan(String value) {
            addCriterion("deskripsi >", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiGreaterThanOrEqualTo(String value) {
            addCriterion("deskripsi >=", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiLessThan(String value) {
            addCriterion("deskripsi <", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiLessThanOrEqualTo(String value) {
            addCriterion("deskripsi <=", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiLike(String value) {
            addCriterion("deskripsi like", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiNotLike(String value) {
            addCriterion("deskripsi not like", value, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiIn(List<String> values) {
            addCriterion("deskripsi in", values, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiNotIn(List<String> values) {
            addCriterion("deskripsi not in", values, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiBetween(String value1, String value2) {
            addCriterion("deskripsi between", value1, value2, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiNotBetween(String value1, String value2) {
            addCriterion("deskripsi not between", value1, value2, "deskripsi");
            return (Criteria) this;
        }

        public Criteria andDeskripsiLikeInsensitive(String value) {
            addCriterion("upper(deskripsi) like", value.toUpperCase(), "deskripsi");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table indikator_tkt
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
     * This class corresponds to the database table indikator_tkt
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