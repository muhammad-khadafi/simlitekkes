package com.pusilkom.base.service;

import com.pusilkom.base.dto.RoleDTO;
import com.pusilkom.base.helper.ResponseEntityHelper;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.RoleMapper;
import com.pusilkom.base.model.mapper.StatusTahapPenelitianMapper;
import com.pusilkom.base.model.mapper.UserwebHasRoleMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by muhammad.khadafi on 2020-03-11 08:50:51.
 */

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    UserwebHasRoleMapper userwebHasRoleMapper;
    @Autowired
    StatusTahapPenelitianMapper statusTahapPenelitianMapper;

    public Role getRoleById(Long id) {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdEqualTo(id);
        List<Role> listRole = roleMapper.selectByExample(roleExample);
        return listRole.get(0);
    }

    public void saveRole(RoleDTO roleDTO) {
        logAktivitasService.addLog(roleDTO.getUsername(), "POST", roleDTO.toString(), "/role");
        roleMapper.insert(toRole(roleDTO));
    }

    public Role toRole(RoleDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDTO, Role.class);
    }

    public ResponseEntityHelper deleteRole(Long id, String username) {
        ResponseEntityHelper responseEntityHelper = new ResponseEntityHelper();
        responseEntityHelper.setStatus(200);
        responseEntityHelper.setMessage("OK");

        responseEntityHelper = checkingConstraints(id, responseEntityHelper);
        if (responseEntityHelper.getStatus() == 200) {
            logAktivitasService.addLog(username, "DELETE", id.toString(), "/role");
            roleMapper.deleteByPrimaryKey(id);
        }
        return responseEntityHelper;
    }

    private ResponseEntityHelper checkingConstraints(Long id, ResponseEntityHelper responseEntityHelper) {
        //checking user role
        UserwebHasRoleExample userwebHasRoleExample = new UserwebHasRoleExample();
        userwebHasRoleExample.createCriteria().andIdRoleEqualTo(id);
        List<UserwebHasRole> userwebHasRoles = userwebHasRoleMapper.selectByExample(userwebHasRoleExample);

        //checking status tahap penelitian
        Role role = roleMapper.selectByPrimaryKey(id);

        StatusTahapPenelitianExample statusTahapPenelitianExample = new StatusTahapPenelitianExample();
        statusTahapPenelitianExample.createCriteria().andKodeRole1EqualTo(role.getRoleCode());
        List<StatusTahapPenelitian> statusTahapPenelitians = statusTahapPenelitianMapper.selectByExample(statusTahapPenelitianExample);

        StatusTahapPenelitianExample statusTahapPenelitianExample2 = new StatusTahapPenelitianExample();
        statusTahapPenelitianExample2.createCriteria().andKodeRole2EqualTo(role.getRoleCode());
        List<StatusTahapPenelitian> statusTahapPenelitians2 = statusTahapPenelitianMapper.selectByExample(statusTahapPenelitianExample2);

        if (!userwebHasRoles.isEmpty() || !statusTahapPenelitians.isEmpty() || !statusTahapPenelitians2.isEmpty()) {
            responseEntityHelper.setMessage("Data digunakan sebagai referensi oleh data lain");
            responseEntityHelper.setStatus(409);
        }

        return responseEntityHelper;


    }

    public void updateRole(RoleDTO roleDTO) {
        logAktivitasService.addLog(roleDTO.getUsername(), "PUT", roleDTO.toString(), "/role");
        roleMapper.updateByPrimaryKeySelective(toRole(roleDTO));
    }

    public List<Role> getRoleList() {
        return roleMapper.selectByExample(new RoleExample());
    }

    public Role getRoleByRoleCode(String usercode) {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRoleCodeEqualTo(usercode);
        List<Role> listRole = roleMapper.selectByExample(roleExample);
        return listRole.get(0);
    }
}
