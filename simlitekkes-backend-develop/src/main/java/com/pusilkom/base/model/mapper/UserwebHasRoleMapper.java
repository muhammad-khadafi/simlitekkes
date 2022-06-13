package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.UserRoleDTO;
import com.pusilkom.base.model.UserwebHasRole;
import com.pusilkom.base.model.UserwebHasRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by muhammad.khadafi on 21/01/2020.
 */
public interface UserwebHasRoleMapper extends BaseMapper<UserwebHasRole, Long, UserwebHasRoleExample> {

    List<UserRoleDTO> getUserwebHasRole();

    Integer checkingRiwayatPenugasan(@Param("idUserweb") Long idUserweb);

    List<UserRoleDTO> getUserwebHasRoleByOrganization(@Param("idOrganization") Integer organizationId);

    List<UserRoleDTO> getUserRoleAll(@Param("organizationId") Integer organizationId,
                                     @Param("roleCode") String roleCode);
}
