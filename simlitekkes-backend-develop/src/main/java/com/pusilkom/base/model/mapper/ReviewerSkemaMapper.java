package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.*;
import com.pusilkom.base.model.ReviewerSkema;
import com.pusilkom.base.model.ReviewerSkemaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReviewerSkemaMapper extends BaseMapper<ReviewerSkema, Integer, ReviewerSkemaExample> {

    List<ReviewerSkemaDTO> getReviewerHasPenugasan(@Param("idUser") Integer idUser);

    //============================AFTER REDEFINED REQS============================================

    List<ReviewerDTO> getReviewerInternalByOrganization(@Param("idOrganisasi") Integer idOrganisasi, @Param("tahunUsulan") String tahunUsulan);

    List<ReviewerDTO> getReviewerEksternal(@Param("tahunUsulan") String tahunUsulan);

    List<ReviewerDTO> getAllReviewerForPlotting(@Param("idOrganisasi") Integer idOrganisasi, @Param("tahunUsulan") String tahunUsulan);

    List<PlottingReviewerDTO> getAllProposalForPlotting(@Param("idOrganisasi") Integer idOrganisasi, @Param("tahunUsulan") String tahunUsulan);

    List<PlottingReviewerDTO> getRiwayatReview(@Param("idUser") Integer idUser);

    List<PlottingReviewerDTO> getRiwayatReviewByTahunUsulan(@Param("tahunUsulan") String tahunUsulan, @Param("idPeneliti") Integer idPeneliti);

    List<HasilReviewDTO> getHasilReviewUsulan(@Param("idSkemaPenelitian") Integer idSkemaPenelitian, @Param("idPenelitian") Integer idPenelitian);

    List<DaftarReviewDTO> getDaftarReview(@Param("idUser") Integer idUser, @Param("tahunUsulan") String tahunUsulan);
}
