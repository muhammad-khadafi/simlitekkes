/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.DashboardTableDTO;
import com.pusilkom.base.dto.IdentitasUsulanPreviewDTO;
import com.pusilkom.base.dto.LaporanAnggaranDTO;
import com.pusilkom.base.dto.PenelitianDTO;
import com.pusilkom.base.model.Penelitian;
import com.pusilkom.base.model.PenelitianExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Helmi Fakhriandi
 */
public interface PenelitianMapper extends BaseMapper<Penelitian, Integer, PenelitianExample>{
    List<PenelitianDTO> getListUsulan(@Param("idUser") Integer idUser, @Param("isEnd") Boolean isEnd);
    List<PenelitianDTO> getRekap(@Param("idOrganisasi") Integer idOrganisasi);
    List<PenelitianDTO> getDaftarBySkemaTahun(@Param("idOrganisasi") Integer idOrganisasi, 
            @Param("idSkemaPenelitian") Integer idSkemaPenelitian,
            @Param("tahunUsulan") String tahunUsulan,
            @Param("tahunMulai") String tahunMulai,
            @Param("tahunSelesai") String tahunSelesai);
    List<PenelitianDTO> getListByIdPeneliti(@Param("idUser") Integer idUser);
    List<PenelitianDTO> getPenelitianDetailStatusById(@Param("idPenelitian") Integer idPenelitian);
    List<PenelitianDTO> getDataValidasiIsPenelitianSubmittedD14R(@Param("idPenelitian") Integer idPenelitian, @Param("idOrganisasi") Integer idOrganisasi, @Param("tahun") String tahun);
    List<PenelitianDTO> getDataValidasiIsPenelitianSubmittedF14R(@Param("idPenelitian") Integer idPenelitian, @Param("tahun") String tahun);
    List<PenelitianDTO> getDataValidasiIsPenelitianNotSubmittedH14W(@Param("idPenelitian") Integer idPenelitian, @Param("userid") Integer userid);
    List<PenelitianDTO> getDataValidasiIsPenelitianAssignedI14R(@Param("idUser") Integer idUser, @Param("tahunUsulan") String tahunUsulan);       
    List<PenelitianDTO> getDataValidasiIsOwnPenelitianH14R(@Param("idUser") Integer idUser, @Param("idPenelitian") Integer idPenelitian);
    List<PenelitianDTO> getDataValidasiOutputPenelitianPeranKetuaH16W(@Param("idUser") Integer idUser, @Param("idOutput") Integer idOutput);
    List<PenelitianDTO> getDataValidasiI14W(@Param("idPenelitian") Integer idPenelitian, @Param("idUser") Integer idUser);

    List<DashboardTableDTO> getProposalPerSkema(@Param("idOrganisasi") Integer idOrganisasi, @Param("tahunUsulan") String tahunUsulan);
    List<DashboardTableDTO> getProposalPerKelompokTkt(@Param("idOrganisasi") Integer idOrganisasi, @Param("tahunUsulan") String tahunUsulan);
    List<DashboardTableDTO> getProposalPerTopik(@Param("idOrganisasi") Integer idOrganisasi, @Param("tahunUsulan") String tahunUsulan);
    List<DashboardTableDTO> getProposalPerTema(@Param("idOrganisasi") Integer idOrganisasi, @Param("tahunUsulan") String tahunUsulan);

    IdentitasUsulanPreviewDTO getIdentitasUsulanPreview(@Param("idPenelitian") Integer idPenelitian);

    List<PenelitianDTO> getTahunUsulanForLaporanAnggaran(@Param("idOrganisasi") Integer idOrganisasi);
    List<LaporanAnggaranDTO> getLaporanAnggaran(@Param("idOrganisasi") Integer idOrganisasi, @Param("tahunUsulan") String tahunUsulan);
}
