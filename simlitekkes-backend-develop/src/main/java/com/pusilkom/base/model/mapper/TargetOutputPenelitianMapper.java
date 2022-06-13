/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.TargetOutputPenelitianDTO;
import com.pusilkom.base.model.TargetOutputPenelitian;
import com.pusilkom.base.model.TargetOutputPenelitianExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Helmi Fakhriandi
 */
public interface TargetOutputPenelitianMapper extends BaseMapper<TargetOutputPenelitian, Integer, TargetOutputPenelitianExample> {
    public List<TargetOutputPenelitianDTO> getListTargetOutputPenelitian(@Param("idPenelitian") Integer idPenelitian);
}
