package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.BukuDTO;
import com.pusilkom.base.model.Buku;
import com.pusilkom.base.model.BukuExample;
import com.pusilkom.base.model.StatusTahapPenelitian;
import com.pusilkom.base.model.StatusTahapPenelitianExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ars on 04/16/2020.
 */
public interface StatusTahapPenelitianMapper extends BaseMapper<StatusTahapPenelitian, Integer, StatusTahapPenelitianExample>  {
    List<StatusTahapPenelitian> getListStatusTahapPenelitianBaru(@Param("idStatusSaatIni") Integer idStatusSaatIni, @Param("role") String role, @Param("kodeStatus") String kodeStatus);
}
