package com.jyu.fire.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jyu.fire.mapper.DepartmentMapper;
import com.jyu.fire.mapper.ManagementMapper;
import com.jyu.fire.pojo.Device;
import com.jyu.fire.pojo.Management;
import com.jyu.fire.service.DepartmentService;
import com.jyu.fire.service.ManagementService;
import com.jyu.fire.vo.DeviceVo;
import com.jyu.fire.vo.ManagementVo;
import com.jyu.fire.vo.Result;
import com.jyu.fire.vo.params.PageParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagementServiceImpl implements ManagementService {

    @Autowired
    ManagementMapper managementMapper;
    @Autowired
    DepartmentMapper departmentMapper;





    @Override
    public Result listManagement(PageParams pageParams) {
        //使用mp分页查询出management表
        Page<Management> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        Page<Management> managementPage = managementMapper.selectPage(page, null);
        List<Management> records = managementPage.getRecords();
        //将内容转换为vo对象
        List<ManagementVo> managementVoList = copyList(records);

        return Result.success(managementVoList);
    }

    @Override
    public Result select(ManagementVo managementVo) {
        Management copy = copy(managementVo);
        Management management = managementMapper.select(copy);
        if (management != null) {
            return Result.success(management);
        }
        return Result.fail("查询不到该人员信息");
    }

    @Override
    public Result delete(int id) {
        int result = managementMapper.deleteById(id);
        if (result == 1) {
            return Result.success(null);
        }
        return Result.fail("删除失败");
    }

    @Override
    public Result insert(ManagementVo managementVo) {
        Management management = copy(managementVo);
        int insert = managementMapper.insert(management);
        if (insert == 1) {
            return Result.success(null);
        }
        return Result.fail("添加失败");
    }

    @Override
    public Result update(ManagementVo managementVo) {
        Management management = copy(managementVo);
        int update = managementMapper.updateById(management);
        if (update == 1) {
            return Result.success(managementMapper.selectById(management.getId()));
        }
        return Result.fail("更新成功");
    }

    private List<ManagementVo> copyList(List<Management> records) {
        List<ManagementVo> managementVoList = new ArrayList<>();
        for (Management record : records) {
            managementVoList.add(copy(record));
        }
        return managementVoList;
    }


    //将Management转换为ManagementVo返回给用户看
    private ManagementVo copy(Management management) {
        ManagementVo managementVo = new ManagementVo();
        BeanUtils.copyProperties(management,managementVo);
        //查询出该人员所属部门名称
        managementVo.setDepartment(departmentMapper.selectDepartmentNameById(management.getDepartmentId()));
        return managementVo;
    }

    //将用户输入的ManagementVo转换为Management对象
    private Management copy(ManagementVo managementVo) {
        Management management = new Management();
        BeanUtils.copyProperties(managementVo,management);
        //根据部门名称查询出该部门的Id
        management.setId((long) departmentMapper.selectIdByName(managementVo.getDepartment()));
        return management;
    }

}
