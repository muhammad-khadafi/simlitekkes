package com.pusilkom.base.model.mapper;

import com.pusilkom.base.model.JadwalKegiatan;
import com.pusilkom.base.model.JadwalKegiatanExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ars on 04/24/2020.
 */
public interface JadwalKegiatanMapper extends BaseMapper<JadwalKegiatan, Integer, JadwalKegiatanExample>  {
    @Select("select distinct tahun " +
            "from jadwal_kegiatan where id_penelitian = #{idPenelitian} " +
            "order by tahun desc")
    List<Integer> getListTahunJadwalKegiatanByIdPenelitian(@Param("idPenelitian") Integer idPenelitian);
}
