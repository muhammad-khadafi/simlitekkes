package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.JurnalDTO;
import com.pusilkom.base.model.Jurnal;
import com.pusilkom.base.model.JurnalExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ars on 04/02/2020.
 */
public interface JurnalMapper extends BaseMapper<Jurnal, Integer, JurnalExample>  {
    List<JurnalDTO> getListJurnalDTO(@Param("searchForm") JurnalDTO searchForm);
}
