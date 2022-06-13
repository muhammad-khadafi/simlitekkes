package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.BeritaDTO;
import com.pusilkom.base.model.Berita;
import com.pusilkom.base.model.BeritaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BeritaMapper extends BaseMapper<Berita, Integer, BeritaExample> {
    List<BeritaDTO> getBeritaTerkini(@Param("idOrganisasi") Integer idOrganisasi);
    List<BeritaDTO> getAllBerita();
    List<BeritaDTO> getBeritaForNonPpsdm(@Param("idOrganisasi") Integer idOrganisasi, @Param("roleCode") String roleCode);
    BeritaDTO getDetailBeritaById(@Param("idBerita") Integer idBerita);
}