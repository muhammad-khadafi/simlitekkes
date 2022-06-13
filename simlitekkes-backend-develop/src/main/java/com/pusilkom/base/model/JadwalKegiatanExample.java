package com.pusilkom.base.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class JadwalKegiatanExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jadwal_kegiatan
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jadwal_kegiatan
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jadwal_kegiatan
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jadwal_kegiatan
     *
     * @mbg.generated
     */
    public JadwalKegiatanExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jadwal_kegiatan
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jadwal_kegiatan
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jadwal_kegiatan
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jadwal_kegiatan
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jadwal_kegiatan
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jadwal_kegiatan
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jadwal_kegiatan
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
     * This method corresponds to the database table jadwal_kegiatan
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
     * This method corresponds to the database table jadwal_kegiatan
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jadwal_kegiatan
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
     * This class corresponds to the database table jadwal_kegiatan
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andTanggalAwalJadwalIsNull() {
            addCriterion("tanggal_awal_jadwal is null");
            return (Criteria) this;
        }

        public Criteria andTanggalAwalJadwalIsNotNull() {
            addCriterion("tanggal_awal_jadwal is not null");
            return (Criteria) this;
        }

        public Criteria andTanggalAwalJadwalEqualTo(Date value) {
            addCriterionForJDBCDate("tanggal_awal_jadwal =", value, "tanggalAwalJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAwalJadwalNotEqualTo(Date value) {
            addCriterionForJDBCDate("tanggal_awal_jadwal <>", value, "tanggalAwalJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAwalJadwalGreaterThan(Date value) {
            addCriterionForJDBCDate("tanggal_awal_jadwal >", value, "tanggalAwalJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAwalJadwalGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("tanggal_awal_jadwal >=", value, "tanggalAwalJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAwalJadwalLessThan(Date value) {
            addCriterionForJDBCDate("tanggal_awal_jadwal <", value, "tanggalAwalJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAwalJadwalLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("tanggal_awal_jadwal <=", value, "tanggalAwalJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAwalJadwalIn(List<Date> values) {
            addCriterionForJDBCDate("tanggal_awal_jadwal in", values, "tanggalAwalJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAwalJadwalNotIn(List<Date> values) {
            addCriterionForJDBCDate("tanggal_awal_jadwal not in", values, "tanggalAwalJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAwalJadwalBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("tanggal_awal_jadwal between", value1, value2, "tanggalAwalJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAwalJadwalNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("tanggal_awal_jadwal not between", value1, value2, "tanggalAwalJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAkhirJadwalIsNull() {
            addCriterion("tanggal_akhir_jadwal is null");
            return (Criteria) this;
        }

        public Criteria andTanggalAkhirJadwalIsNotNull() {
            addCriterion("tanggal_akhir_jadwal is not null");
            return (Criteria) this;
        }

        public Criteria andTanggalAkhirJadwalEqualTo(Date value) {
            addCriterionForJDBCDate("tanggal_akhir_jadwal =", value, "tanggalAkhirJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAkhirJadwalNotEqualTo(Date value) {
            addCriterionForJDBCDate("tanggal_akhir_jadwal <>", value, "tanggalAkhirJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAkhirJadwalGreaterThan(Date value) {
            addCriterionForJDBCDate("tanggal_akhir_jadwal >", value, "tanggalAkhirJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAkhirJadwalGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("tanggal_akhir_jadwal >=", value, "tanggalAkhirJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAkhirJadwalLessThan(Date value) {
            addCriterionForJDBCDate("tanggal_akhir_jadwal <", value, "tanggalAkhirJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAkhirJadwalLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("tanggal_akhir_jadwal <=", value, "tanggalAkhirJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAkhirJadwalIn(List<Date> values) {
            addCriterionForJDBCDate("tanggal_akhir_jadwal in", values, "tanggalAkhirJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAkhirJadwalNotIn(List<Date> values) {
            addCriterionForJDBCDate("tanggal_akhir_jadwal not in", values, "tanggalAkhirJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAkhirJadwalBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("tanggal_akhir_jadwal between", value1, value2, "tanggalAkhirJadwal");
            return (Criteria) this;
        }

        public Criteria andTanggalAkhirJadwalNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("tanggal_akhir_jadwal not between", value1, value2, "tanggalAkhirJadwal");
            return (Criteria) this;
        }

        public Criteria andTahunIsNull() {
            addCriterion("tahun is null");
            return (Criteria) this;
        }

        public Criteria andTahunIsNotNull() {
            addCriterion("tahun is not null");
            return (Criteria) this;
        }

        public Criteria andTahunEqualTo(Short value) {
            addCriterion("tahun =", value, "tahun");
            return (Criteria) this;
        }

        public Criteria andTahunNotEqualTo(Short value) {
            addCriterion("tahun <>", value, "tahun");
            return (Criteria) this;
        }

        public Criteria andTahunGreaterThan(Short value) {
            addCriterion("tahun >", value, "tahun");
            return (Criteria) this;
        }

        public Criteria andTahunGreaterThanOrEqualTo(Short value) {
            addCriterion("tahun >=", value, "tahun");
            return (Criteria) this;
        }

        public Criteria andTahunLessThan(Short value) {
            addCriterion("tahun <", value, "tahun");
            return (Criteria) this;
        }

        public Criteria andTahunLessThanOrEqualTo(Short value) {
            addCriterion("tahun <=", value, "tahun");
            return (Criteria) this;
        }

        public Criteria andTahunIn(List<Short> values) {
            addCriterion("tahun in", values, "tahun");
            return (Criteria) this;
        }

        public Criteria andTahunNotIn(List<Short> values) {
            addCriterion("tahun not in", values, "tahun");
            return (Criteria) this;
        }

        public Criteria andTahunBetween(Short value1, Short value2) {
            addCriterion("tahun between", value1, value2, "tahun");
            return (Criteria) this;
        }

        public Criteria andTahunNotBetween(Short value1, Short value2) {
            addCriterion("tahun not between", value1, value2, "tahun");
            return (Criteria) this;
        }

        public Criteria andNamaKegiatanIsNull() {
            addCriterion("nama_kegiatan is null");
            return (Criteria) this;
        }

        public Criteria andNamaKegiatanIsNotNull() {
            addCriterion("nama_kegiatan is not null");
            return (Criteria) this;
        }

        public Criteria andNamaKegiatanEqualTo(String value) {
            addCriterion("nama_kegiatan =", value, "namaKegiatan");
            return (Criteria) this;
        }

        public Criteria andNamaKegiatanNotEqualTo(String value) {
            addCriterion("nama_kegiatan <>", value, "namaKegiatan");
            return (Criteria) this;
        }

        public Criteria andNamaKegiatanGreaterThan(String value) {
            addCriterion("nama_kegiatan >", value, "namaKegiatan");
            return (Criteria) this;
        }

        public Criteria andNamaKegiatanGreaterThanOrEqualTo(String value) {
            addCriterion("nama_kegiatan >=", value, "namaKegiatan");
            return (Criteria) this;
        }

        public Criteria andNamaKegiatanLessThan(String value) {
            addCriterion("nama_kegiatan <", value, "namaKegiatan");
            return (Criteria) this;
        }

        public Criteria andNamaKegiatanLessThanOrEqualTo(String value) {
            addCriterion("nama_kegiatan <=", value, "namaKegiatan");
            return (Criteria) this;
        }

        public Criteria andNamaKegiatanLike(String value) {
            addCriterion("nama_kegiatan like", value, "namaKegiatan");
            return (Criteria) this;
        }

        public Criteria andNamaKegiatanNotLike(String value) {
            addCriterion("nama_kegiatan not like", value, "namaKegiatan");
            return (Criteria) this;
        }

        public Criteria andNamaKegiatanIn(List<String> values) {
            addCriterion("nama_kegiatan in", values, "namaKegiatan");
            return (Criteria) this;
        }

        public Criteria andNamaKegiatanNotIn(List<String> values) {
            addCriterion("nama_kegiatan not in", values, "namaKegiatan");
            return (Criteria) this;
        }

        public Criteria andNamaKegiatanBetween(String value1, String value2) {
            addCriterion("nama_kegiatan between", value1, value2, "namaKegiatan");
            return (Criteria) this;
        }

        public Criteria andNamaKegiatanNotBetween(String value1, String value2) {
            addCriterion("nama_kegiatan not between", value1, value2, "namaKegiatan");
            return (Criteria) this;
        }

        public Criteria andNamaKegiatanLikeInsensitive(String value) {
            addCriterion("upper(nama_kegiatan) like", value.toUpperCase(), "namaKegiatan");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table jadwal_kegiatan
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
     * This class corresponds to the database table jadwal_kegiatan
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