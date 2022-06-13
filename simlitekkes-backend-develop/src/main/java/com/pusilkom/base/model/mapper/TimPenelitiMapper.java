/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.TimPenelitiDTO;
import com.pusilkom.base.model.TimPeneliti;
import com.pusilkom.base.model.TimPenelitiExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Helmi Fakhriandi
 */
public interface TimPenelitiMapper extends BaseMapper<TimPeneliti, Integer, TimPenelitiExample> {
    List<TimPenelitiDTO> getDataKetuaFromIdPenelitian(@Param("idPenelitian") Integer idPenelitian);
}
