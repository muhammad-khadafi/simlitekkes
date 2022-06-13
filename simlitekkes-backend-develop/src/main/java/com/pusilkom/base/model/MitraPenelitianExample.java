package com.pusilkom.base.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MitraPenelitianExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mitra_penelitian
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mitra_penelitian
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mitra_penelitian
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mitra_penelitian
     *
     * @mbg.generated
     */
    public MitraPenelitianExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mitra_penelitian
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mitra_penelitian
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mitra_penelitian
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mitra_penelitian
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mitra_penelitian
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mitra_penelitian
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mitra_penelitian
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
     * This method corresponds to the database table mitra_penelitian
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
     * This method corresponds to the database table mitra_penelitian
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mitra_penelitian
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
     * This class corresponds to the database table mitra_penelitian
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

        public Criteria andIdJenisMitraIsNull() {
            addCriterion("id_jenis_mitra is null");
            return (Criteria) this;
        }

        public Criteria andIdJenisMitraIsNotNull() {
            addCriterion("id_jenis_mitra is not null");
            return (Criteria) this;
        }

        public Criteria andIdJenisMitraEqualTo(Integer value) {
            addCriterion("id_jenis_mitra =", value, "idJenisMitra");
            return (Criteria) this;
        }

        public Criteria andIdJenisMitraNotEqualTo(Integer value) {
            addCriterion("id_jenis_mitra <>", value, "idJenisMitra");
            return (Criteria) this;
        }

        public Criteria andIdJenisMitraGreaterThan(Integer value) {
            addCriterion("id_jenis_mitra >", value, "idJenisMitra");
            return (Criteria) this;
        }

        public Criteria andIdJenisMitraGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_jenis_mitra >=", value, "idJenisMitra");
            return (Criteria) this;
        }

        public Criteria andIdJenisMitraLessThan(Integer value) {
            addCriterion("id_jenis_mitra <", value, "idJenisMitra");
            return (Criteria) this;
        }

        public Criteria andIdJenisMitraLessThanOrEqualTo(Integer value) {
            addCriterion("id_jenis_mitra <=", value, "idJenisMitra");
            return (Criteria) this;
        }

        public Criteria andIdJenisMitraIn(List<Integer> values) {
            addCriterion("id_jenis_mitra in", values, "idJenisMitra");
            return (Criteria) this;
        }

        public Criteria andIdJenisMitraNotIn(List<Integer> values) {
            addCriterion("id_jenis_mitra not in", values, "idJenisMitra");
            return (Criteria) this;
        }

        public Criteria andIdJenisMitraBetween(Integer value1, Integer value2) {
            addCriterion("id_jenis_mitra between", value1, value2, "idJenisMitra");
            return (Criteria) this;
        }

        public Criteria andIdJenisMitraNotBetween(Integer value1, Integer value2) {
            addCriterion("id_jenis_mitra not between", value1, value2, "idJenisMitra");
            return (Criteria) this;
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

        public Criteria andNamaMitraIsNull() {
            addCriterion("nama_mitra is null");
            return (Criteria) this;
        }

        public Criteria andNamaMitraIsNotNull() {
            addCriterion("nama_mitra is not null");
            return (Criteria) this;
        }

        public Criteria andNamaMitraEqualTo(String value) {
            addCriterion("nama_mitra =", value, "namaMitra");
            return (Criteria) this;
        }

        public Criteria andNamaMitraNotEqualTo(String value) {
            addCriterion("nama_mitra <>", value, "namaMitra");
            return (Criteria) this;
        }

        public Criteria andNamaMitraGreaterThan(String value) {
            addCriterion("nama_mitra >", value, "namaMitra");
            return (Criteria) this;
        }

        public Criteria andNamaMitraGreaterThanOrEqualTo(String value) {
            addCriterion("nama_mitra >=", value, "namaMitra");
            return (Criteria) this;
        }

        public Criteria andNamaMitraLessThan(String value) {
            addCriterion("nama_mitra <", value, "namaMitra");
            return (Criteria) this;
        }

        public Criteria andNamaMitraLessThanOrEqualTo(String value) {
            addCriterion("nama_mitra <=", value, "namaMitra");
            return (Criteria) this;
        }

        public Criteria andNamaMitraLike(String value) {
            addCriterion("nama_mitra like", value, "namaMitra");
            return (Criteria) this;
        }

        public Criteria andNamaMitraNotLike(String value) {
            addCriterion("nama_mitra not like", value, "namaMitra");
            return (Criteria) this;
        }

        public Criteria andNamaMitraIn(List<String> values) {
            addCriterion("nama_mitra in", values, "namaMitra");
            return (Criteria) this;
        }

        public Criteria andNamaMitraNotIn(List<String> values) {
            addCriterion("nama_mitra not in", values, "namaMitra");
            return (Criteria) this;
        }

        public Criteria andNamaMitraBetween(String value1, String value2) {
            addCriterion("nama_mitra between", value1, value2, "namaMitra");
            return (Criteria) this;
        }

        public Criteria andNamaMitraNotBetween(String value1, String value2) {
            addCriterion("nama_mitra not between", value1, value2, "namaMitra");
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

        public Criteria andPenanggungJawabIsNull() {
            addCriterion("penanggung_jawab is null");
            return (Criteria) this;
        }

        public Criteria andPenanggungJawabIsNotNull() {
            addCriterion("penanggung_jawab is not null");
            return (Criteria) this;
        }

        public Criteria andPenanggungJawabEqualTo(String value) {
            addCriterion("penanggung_jawab =", value, "penanggungJawab");
            return (Criteria) this;
        }

        public Criteria andPenanggungJawabNotEqualTo(String value) {
            addCriterion("penanggung_jawab <>", value, "penanggungJawab");
            return (Criteria) this;
        }

        public Criteria andPenanggungJawabGreaterThan(String value) {
            addCriterion("penanggung_jawab >", value, "penanggungJawab");
            return (Criteria) this;
        }

        public Criteria andPenanggungJawabGreaterThanOrEqualTo(String value) {
            addCriterion("penanggung_jawab >=", value, "penanggungJawab");
            return (Criteria) this;
        }

        public Criteria andPenanggungJawabLessThan(String value) {
            addCriterion("penanggung_jawab <", value, "penanggungJawab");
            return (Criteria) this;
        }

        public Criteria andPenanggungJawabLessThanOrEqualTo(String value) {
            addCriterion("penanggung_jawab <=", value, "penanggungJawab");
            return (Criteria) this;
        }

        public Criteria andPenanggungJawabLike(String value) {
            addCriterion("penanggung_jawab like", value, "penanggungJawab");
            return (Criteria) this;
        }

        public Criteria andPenanggungJawabNotLike(String value) {
            addCriterion("penanggung_jawab not like", value, "penanggungJawab");
            return (Criteria) this;
        }

        public Criteria andPenanggungJawabIn(List<String> values) {
            addCriterion("penanggung_jawab in", values, "penanggungJawab");
            return (Criteria) this;
        }

        public Criteria andPenanggungJawabNotIn(List<String> values) {
            addCriterion("penanggung_jawab not in", values, "penanggungJawab");
            return (Criteria) this;
        }

        public Criteria andPenanggungJawabBetween(String value1, String value2) {
            addCriterion("penanggung_jawab between", value1, value2, "penanggungJawab");
            return (Criteria) this;
        }

        public Criteria andPenanggungJawabNotBetween(String value1, String value2) {
            addCriterion("penanggung_jawab not between", value1, value2, "penanggungJawab");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNull() {
            addCriterion("file_path is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("file_path is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("file_path =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("file_path <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("file_path >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("file_path >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("file_path <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("file_path <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("file_path like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("file_path not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("file_path in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("file_path not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("file_path between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("file_path not between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andTimestampUploadIsNull() {
            addCriterion("timestamp_upload is null");
            return (Criteria) this;
        }

        public Criteria andTimestampUploadIsNotNull() {
            addCriterion("timestamp_upload is not null");
            return (Criteria) this;
        }

        public Criteria andTimestampUploadEqualTo(Date value) {
            addCriterion("timestamp_upload =", value, "timestampUpload");
            return (Criteria) this;
        }

        public Criteria andTimestampUploadNotEqualTo(Date value) {
            addCriterion("timestamp_upload <>", value, "timestampUpload");
            return (Criteria) this;
        }

        public Criteria andTimestampUploadGreaterThan(Date value) {
            addCriterion("timestamp_upload >", value, "timestampUpload");
            return (Criteria) this;
        }

        public Criteria andTimestampUploadGreaterThanOrEqualTo(Date value) {
            addCriterion("timestamp_upload >=", value, "timestampUpload");
            return (Criteria) this;
        }

        public Criteria andTimestampUploadLessThan(Date value) {
            addCriterion("timestamp_upload <", value, "timestampUpload");
            return (Criteria) this;
        }

        public Criteria andTimestampUploadLessThanOrEqualTo(Date value) {
            addCriterion("timestamp_upload <=", value, "timestampUpload");
            return (Criteria) this;
        }

        public Criteria andTimestampUploadIn(List<Date> values) {
            addCriterion("timestamp_upload in", values, "timestampUpload");
            return (Criteria) this;
        }

        public Criteria andTimestampUploadNotIn(List<Date> values) {
            addCriterion("timestamp_upload not in", values, "timestampUpload");
            return (Criteria) this;
        }

        public Criteria andTimestampUploadBetween(Date value1, Date value2) {
            addCriterion("timestamp_upload between", value1, value2, "timestampUpload");
            return (Criteria) this;
        }

        public Criteria andTimestampUploadNotBetween(Date value1, Date value2) {
            addCriterion("timestamp_upload not between", value1, value2, "timestampUpload");
            return (Criteria) this;
        }

        public Criteria andNamaMitraLikeInsensitive(String value) {
            addCriterion("upper(nama_mitra) like", value.toUpperCase(), "namaMitra");
            return (Criteria) this;
        }

        public Criteria andEmailLikeInsensitive(String value) {
            addCriterion("upper(email) like", value.toUpperCase(), "email");
            return (Criteria) this;
        }

        public Criteria andPenanggungJawabLikeInsensitive(String value) {
            addCriterion("upper(penanggung_jawab) like", value.toUpperCase(), "penanggungJawab");
            return (Criteria) this;
        }

        public Criteria andFilePathLikeInsensitive(String value) {
            addCriterion("upper(file_path) like", value.toUpperCase(), "filePath");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table mitra_penelitian
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
     * This class corresponds to the database table mitra_penelitian
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