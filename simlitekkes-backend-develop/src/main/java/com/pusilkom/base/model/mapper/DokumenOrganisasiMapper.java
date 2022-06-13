package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.DokumenOrganisasiDTO;
import com.pusilkom.base.model.DokumenOrganisasi;
import com.pusilkom.base.model.DokumenOrganisasiExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DokumenOrganisasiMapper extends BaseMapper<DokumenOrganisasi, Integer, DokumenOrganisasiExample> {

    List<DokumenOrganisasiDTO> getListData(@Param("idOrganisasi") Integer idOrganisasi);

    List<DokumenOrganisasiDTO> getDokumenPenugasanReviewer(@Param("idOrganisasi") Integer idOrganisasi, @Param("tahunUsulan") String tahunUsulan, @Param("kataKunci") String kataKunci);

    List<DokumenOrganisasiDTO> getDokumenTerkini(@Param("idOrganisasi") Integer idOrganisasi);
}
