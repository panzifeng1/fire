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
import com.jyu.fire.vo.ListManagementResult;
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
    public ListManagementResult listManagement(PageParams pageParams) {
        //使用mp分页查询出management表
        Page<Management> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        Page<Management> managementPage = managementMapper.selectPage(page, null);
        List<Management> records = managementPage.getRecords();
        //将内容转换为vo对象
        List<ManagementVo> managementVoList = copyList(records);
        long total = managementPage.getTotal();
        long current = managementPage.getCurrent();
        long pages = managementPage.getPages();
        return ListManagementResult.success(managementVoList,total,current,pages);
    }

    @Override
    public Result select(ManagementVo managementVo) {
        //将前端传过来的数据转换为后端对应表的数据，主要是部门ID跟部门名称的对应
        Management copy = copy(managementVo);
        List<Management> select = managementMapper.select(copy);
        //查询不到数据
        if (select == null) {
            return Result.fail("查询不到该人员信息");
        }
        //将查询到的数据重新封装为前端数据返回
        List<ManagementVo> managementVoList = copyList(select);
        if (managementVoList != null) {
            return Result.success(managementVoList);
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
        //在添加之前先判断是否已存在该人员编号
        Management selectByNum = managementMapper.selectByNum(managementVo.getNum());
        if (selectByNum != null) {
            return Result.fail("该人员编号已存在，不可重复添加");
        }

        Management management = copy(managementVo);
        management.setStatus(0);

        int insert = managementMapper.insert(management);
        if (insert == 1) {
            return Result.success(null);
        }
        return Result.fail("添加失败");
    }

    @Override
    public Result update(ManagementVo managementVo) {
        Management selectByNum = managementMapper.selectByNum(managementVo.getNum());
        if (selectByNum != null) {
            return Result.fail("该人员编号已存在，修改失败");
        }
        Management management = copy(managementVo);
        int update = managementMapper.updateById(management);
        if (update == 1) {
            Management management1 = managementMapper.selectById(management.getId());
            ManagementVo copy = copy(management1);
            return Result.success(copy);
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
        String departmentName = managementVo.getDepartment();
        if (departmentName != "") {
            management.setDepartmentId(departmentMapper.selectIdByName(managementVo.getDepartment()));
        }
        return management;
    }

}
