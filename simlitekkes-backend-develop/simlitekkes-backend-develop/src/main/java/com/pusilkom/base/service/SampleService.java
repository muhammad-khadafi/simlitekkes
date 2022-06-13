package com.pusilkom.base.service;

import com.pusilkom.base.dto.EmployeeDTO;
import com.pusilkom.base.dto.MenuGroup;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhammad.khadafi on 26/09/2019.
 */

@Service
@Transactional
public class SampleService {

    @Autowired
    SampleTableMapper sampleTableMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    FileMapper fileMapper;

    @Autowired
    MenuMapper menuMapper;

    public SampleTable getData(){
        return sampleTableMapper.selectByPrimaryKey(1L);
    }

    public User findByUsername(String username){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> listUser = userMapper.selectByExample(userExample);
        return listUser.get(0);
    }

    public List<Employee> getEmployeeDate() throws ParseException {
        return employeeMapper.selectByExample(new EmployeeExample());
    }

    public void saveEmployee(EmployeeDTO employeeDTO) {
        employeeMapper.insert(toEmployee(employeeDTO));
    }

    public Employee toEmployee(EmployeeDTO employeeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employeeDTO, Employee.class);
    }

    public void deleteEmployee(Long id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void updateEmployee(EmployeeDTO employeeDTO) {
        employeeMapper.updateByPrimaryKeySelective(toEmployee(employeeDTO));
    }


    public File getFile(Long id) {
        File fileDoc = fileMapper.selectByPrimaryKey(id);
        return fileDoc;
    }

    public List<File> getFileList() {
        return fileMapper.selectByExample(new FileExample());
    }

    public List<MenuGroup> getMenuList(String role) {

        /**
         * For performance dan layouting reasons menu just have 2 level
         */

        List<MenuGroup> menuGroups = new ArrayList<>();
        List<Menu> listOfParent = menuMapper.getMenuParentList(role);

        for (Menu parent : listOfParent) {
            MenuGroup menuGroup = new MenuGroup();
            MenuExample menuExample = new MenuExample();
            menuExample.createCriteria().andIdParentEqualTo(parent.getId());
            List<Menu> listOfChild = menuMapper.getMenuChildList(parent.getId(), role);
            menuGroup.setMenuParent(parent);
            menuGroup.setListOfSubMenu(listOfChild);
            menuGroups.add(menuGroup);
        }

        return menuGroups;
    }
}
