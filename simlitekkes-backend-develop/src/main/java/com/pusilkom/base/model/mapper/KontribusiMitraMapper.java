package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.MitraPenelitianDTO;
import com.pusilkom.base.model.KontribusiMitra;
import com.pusilkom.base.model.KontribusiMitraExample;
import com.pusilkom.base.model.MitraPenelitian;
import com.pusilkom.base.model.MitraPenelitianExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ars on 04/16/2020.
 */
public interface KontribusiMitraMapper extends BaseMapper<KontribusiMitra, Integer, KontribusiMitraExample>  {
}
