package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.JurnalDTO;
import com.pusilkom.base.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ars on 04/02/2020.
 */
public interface OutputMapper extends BaseMapper<Output, Integer, OutputExample>  {
    List<Output> getOutputByParam(@Param("idUser") Integer idUser, @Param("idJenisOutput") Integer idJenisOutput);
}
