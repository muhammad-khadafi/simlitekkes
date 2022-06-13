package com.pusilkom.base.model.mapper;

import com.pusilkom.base.dto.MenuDTO;
import com.pusilkom.base.model.Menu;
import com.pusilkom.base.model.MenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by muhammad.khadafi on 21/01/2020.
 */
public interface MenuMapper extends BaseMapper<Menu, Long, MenuExample>  {

    List<MenuDTO> getMenuList();

    List<Menu> getMenuParentList(@Param("role") String role);

    List<Menu> getMenuChildList(@Param("id") Long id, @Param("role") String role);

    List<Menu> getMenusByRoleId(@Param("roleId") Long roleId);
}
