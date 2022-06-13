package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.PengukuranTktDTO;
import com.pusilkom.base.dto.TemaDTO;
import com.pusilkom.base.model.KelompokTkt;
import com.pusilkom.base.model.KelompokTktExample;
import com.pusilkom.base.model.Tema;
import com.pusilkom.base.model.TemaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ars on 30/04/2020.
 */
public interface KelompokTktMapper extends BaseMapper<KelompokTkt, Integer, KelompokTktExample>  {

}
