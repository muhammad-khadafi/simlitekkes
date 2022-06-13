package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.PenelitiDTO;
import com.pusilkom.base.model.Peneliti;
import com.pusilkom.base.model.PenelitiExample;
import com.pusilkom.base.model.RumpunIlmu;
import com.pusilkom.base.model.RumpunIlmuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ars on 11/03/2020.
 */
public interface RumpunIlmuMapper extends BaseMapper<RumpunIlmu, Integer, RumpunIlmuExample>  {
}
