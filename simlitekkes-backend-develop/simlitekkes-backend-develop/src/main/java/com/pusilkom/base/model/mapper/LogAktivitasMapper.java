package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.LogAktifitasDTO;
import com.pusilkom.base.model.LogAktivitas;
import com.pusilkom.base.model.LogAktivitasExample;
import com.pusilkom.base.model.Role;
import com.pusilkom.base.model.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by muhammad.khadafi on 26/09/2019.
 */
public interface LogAktivitasMapper extends BaseMapper<LogAktivitas, Long, LogAktivitasExample> {

    List<LogAktifitasDTO> getLogAktifitasPerBulan(@Param("bulan") Integer bulan, @Param("tahun") Integer tahun);
}
