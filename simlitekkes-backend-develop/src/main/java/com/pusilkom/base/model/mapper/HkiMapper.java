package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.HkiDTO;
import com.pusilkom.base.model.Hki;
import com.pusilkom.base.model.HkiExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ars on 04/02/2020.
 */
public interface HkiMapper extends BaseMapper<Hki, Integer, HkiExample>  {
    List<HkiDTO> getListHkiDTO(@Param("searchForm") HkiDTO searchForm);
}
