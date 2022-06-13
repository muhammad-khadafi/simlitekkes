package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.KriteriaPenilaianPenelitianDTO;
import com.pusilkom.base.model.KriteriaPenilaianPenelitian;
import com.pusilkom.base.model.KriteriaPenilaianPenelitianExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface KriteriaPenilaianPenelitianMapper extends BaseMapper<KriteriaPenilaianPenelitian, Integer, KriteriaPenilaianPenelitianExample> {
    @Select("update kriteria_penilaian_penelitian set id_pilihan_kriteria_penilaian = #{dto.idPilihanKriteriaPenilaian}" +
            ", komentar = #{dto.komentar} where id_penelitian = #{dto.idPenelitian} and id_kriteria_penilaian = #{dto.idKriteriaPenilaian}")
    void updateKriteriaPenilaian(@Param("dto") KriteriaPenilaianPenelitianDTO dto);
}
