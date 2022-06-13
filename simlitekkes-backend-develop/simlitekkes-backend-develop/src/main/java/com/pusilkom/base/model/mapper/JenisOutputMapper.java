package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.JenisOutputDTO;
import com.pusilkom.base.model.JenisOutput;
import com.pusilkom.base.model.JenisOutputExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ars on 04/02/2020.
 */
public interface JenisOutputMapper extends BaseMapper<JenisOutput, Integer, JenisOutputExample>  {
    public List<JenisOutputDTO> getListWajib(@Param("idSkemaPenelitian") Integer idSkemaPenelitian, @Param("idPenelitian") Integer idPenelitian);
    public List<JenisOutputDTO> getListTambahan(@Param("idPenelitian") Integer idPenelitian);
    public List<JenisOutputDTO> getAll();
}
