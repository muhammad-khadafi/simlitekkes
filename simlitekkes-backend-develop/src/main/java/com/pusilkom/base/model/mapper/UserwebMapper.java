package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.UserwebDTO;
import com.pusilkom.base.model.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by muhammad.khadafi on 26/09/2019.
 */
public interface UserwebMapper extends BaseMapper<Userweb, Integer, UserwebExample> {

    Role getRoleByUsername(@Param("username")String username);

    List<Role> getRolesByUsername(@Param("username")String username);

    List<Userweb> getUserwebisNotPeneliti(@Param("idOrganisasi")Integer idOrganisasi);

    @Select("select case when count(*) > 0 then true else false end as isExist\n" +
        "from userweb_has_role uh\n" +
        "left join role r on r.id = uh.id_role\n" +
        "where uh.id_userweb = #{idUser} and r.role_code ilike 'DOSEN'")
    Boolean checkUserwebIsDosen(@Param("idUser")Long idUser);

    List<Userweb> selectSuperAdmin();

    List<UserwebDTO> getUserwebList(@Param("organizationId") Integer organizationId,
                                    @Param("roleCode") String roleCode);

    List<UserwebDTO> getUserwebList2(@Param("organizationId") Integer organizationId,
                                    @Param("roleCode") String roleCode);
}
