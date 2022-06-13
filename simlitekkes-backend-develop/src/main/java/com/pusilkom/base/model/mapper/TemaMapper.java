package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.SkemaPenelitianDTO;
import com.pusilkom.base.dto.TemaDTO;
import com.pusilkom.base.model.SkemaPenelitian;
import com.pusilkom.base.model.SkemaPenelitianExample;
import com.pusilkom.base.model.Tema;
import com.pusilkom.base.model.TemaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ars on 11/03/2020.
 */
public interface TemaMapper extends BaseMapper<Tema, Integer, TemaExample>  {
    List<TemaDTO> getListTemaDTO(@Param("searchForm")TemaDTO searchForm);
}
