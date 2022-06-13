package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.MenuDTO;
import com.pusilkom.base.dto.SkemaPenelitianDTO;
import com.pusilkom.base.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ars on 11/03/2020.
 */
public interface SkemaPenelitianMapper extends BaseMapper<SkemaPenelitian, Integer, SkemaPenelitianExample>  {
    List<SkemaPenelitianDTO> getListSkemaPenelitian(@Param("searchForm")SkemaPenelitianDTO searchForm);
    List<KategoriSkemaPenelitian> getListKategoriByKlasifikasi(@Param("searchForm")SkemaPenelitianDTO searchForm);
}
