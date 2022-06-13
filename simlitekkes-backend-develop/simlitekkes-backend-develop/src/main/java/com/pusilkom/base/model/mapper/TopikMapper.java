package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.TopikDTO;
import com.pusilkom.base.model.Topik;
import com.pusilkom.base.model.TopikExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ars on 11/03/2020.
 */
public interface TopikMapper extends BaseMapper<Topik, Integer, TopikExample>  {
    List<TopikDTO> getListTopikDTO(@Param("searchForm") TopikDTO searchForm);
}
