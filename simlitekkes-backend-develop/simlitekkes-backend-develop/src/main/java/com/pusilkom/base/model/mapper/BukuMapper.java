package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.BukuDTO;
import com.pusilkom.base.model.Buku;
import com.pusilkom.base.model.BukuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ars on 04/16/2020.
 */
public interface BukuMapper extends BaseMapper<Buku, Integer, BukuExample>  {
    List<BukuDTO> getListBukuDTO(@Param("searchForm") BukuDTO searchForm);
}
