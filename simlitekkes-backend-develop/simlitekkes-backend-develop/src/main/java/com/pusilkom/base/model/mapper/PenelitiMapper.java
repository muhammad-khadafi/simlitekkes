package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.DashboardPieChartDTO;
import com.pusilkom.base.dto.DashboardTableDTO;
import com.pusilkom.base.dto.PenelitiDTO;
import com.pusilkom.base.model.Peneliti;
import com.pusilkom.base.model.PenelitiExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ars on 11/03/2020.
 */
public interface PenelitiMapper extends BaseMapper<Peneliti, Integer, PenelitiExample>  {
    List<PenelitiDTO> getListPenelitiDTO(@Param("searchForm") PenelitiDTO searchForm);
    List<PenelitiDTO> getListPenelitiFromIdPenelitian(@Param("idPenelitian") Integer idPenelitian, @Param("isPoltekkes") boolean isPoltekkes);

    List<PenelitiDTO> getDaftarPenelitiReviewerInternal(@Param("idOrganisasi") Integer idOrganisasi, @Param("kataKunci") String kataKunci);
    List<PenelitiDTO> getDaftarPenelitiReviewerEksternal(@Param("kataKunci") String kataKunci);

    List<DashboardPieChartDTO> getPenelitiPerPendidikanTerakhir(@Param("idOrganisasi") Integer idOrganisasi);
    List<DashboardTableDTO> getPenelitiReviewerPerBidangIlmu(@Param("idOrganisasi") Integer idOrganisasi, @Param("tahunUsulan") String tahunUsulan);

    List<PenelitiDTO> getAnggotaPeneliti(@Param("isPoltekkes") Boolean isPoltekkes, @Param("idPenelitian") Integer idPenelitian);

    List<PenelitiDTO> selectTimPeneliti(@Param("idPenelitian") Integer idPenelitian, @Param("isPoltekkes") Boolean isPoltekkes);
}
