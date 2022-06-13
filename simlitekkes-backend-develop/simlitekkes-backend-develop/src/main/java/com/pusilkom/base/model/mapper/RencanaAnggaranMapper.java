/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.RencanaAnggaranDTO;
import com.pusilkom.base.model.RencanaAnggaran;
import com.pusilkom.base.model.RencanaAnggaranExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Helmi Fakhriandi
 */
public interface RencanaAnggaranMapper extends BaseMapper<RencanaAnggaran, Integer, RencanaAnggaranExample> {
    List<RencanaAnggaranDTO> getTotalBiayaPerTahun(@Param("idPenelitian") Integer idPenelitian);
    RencanaAnggaranDTO getTotalBiayaByTahun(@Param("idPenelitian") Integer idPenelitian, @Param("tahun") Short tahun);
    List<RencanaAnggaranDTO> getDetailFromPenelitianTahun(@Param("idPenelitian") Integer idPenelitian, @Param("tahun") Integer tahun);
    List<RencanaAnggaranDTO> getTotalFromPenelitian(@Param("idPenelitian") Integer idPenelitian);
}
