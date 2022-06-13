package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.RoleMenuDTO;
import com.pusilkom.base.model.RoleHasMenu;
import com.pusilkom.base.model.RoleHasMenuExample;

import java.util.List;

/**
 * Created by muhammad.khadafi on 21/01/2020.
 */
public interface RoleHasMenuMapper extends BaseMapper<RoleHasMenu, Long, RoleHasMenuExample> {


    List<RoleMenuDTO> getRoleHasMenu();
}
