package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.OrganisasiDTO;
import com.pusilkom.base.model.Organisasi;
import com.pusilkom.base.model.OrganisasiExample;
import org.apache.ibatis.annotations.Param;

public interface OrganisasiMapper extends BaseMapper<Organisasi, Integer, OrganisasiExample> {

    OrganisasiDTO getTemaTopikUnggulan(@Param("id")Integer id);
}
