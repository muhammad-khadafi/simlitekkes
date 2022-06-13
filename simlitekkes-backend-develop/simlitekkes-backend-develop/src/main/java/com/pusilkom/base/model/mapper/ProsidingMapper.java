package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.ProsidingDTO;
import com.pusilkom.base.model.Prosiding;
import com.pusilkom.base.model.ProsidingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ars on 04/14/2020.
 */
public interface ProsidingMapper extends BaseMapper<Prosiding, Integer, ProsidingExample>  {
    List<ProsidingDTO> getListProsidingDTO(@Param("searchForm") ProsidingDTO searchForm);
}
