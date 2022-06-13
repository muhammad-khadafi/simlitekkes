package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.BukuDTO;
import com.pusilkom.base.dto.MitraPenelitianDTO;
import com.pusilkom.base.model.Buku;
import com.pusilkom.base.model.BukuExample;
import com.pusilkom.base.model.MitraPenelitian;
import com.pusilkom.base.model.MitraPenelitianExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ars on 04/16/2020.
 */
public interface MitraPenelitianMapper extends BaseMapper<MitraPenelitian, Integer, MitraPenelitianExample>  {
    List<MitraPenelitianDTO> getListMitraPenelitianDTO(@Param("idPenelitian") Integer idPenelitian);
    List<MitraPenelitianDTO> getRekapMitraPenelitianFromPenelitian(@Param("idPenelitian") Integer idPenelitian);
}
