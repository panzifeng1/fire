package com.jyu.fire.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jyu.fire.mapper.DeviceMapper;
import com.jyu.fire.mapper.DeviceMsgMapper;
import com.jyu.fire.pojo.Device;
import com.jyu.fire.pojo.DeviceMsg;
import com.jyu.fire.pojo.Management;
import com.jyu.fire.service.DepartmentService;
import com.jyu.fire.service.DeviceService;
import com.jyu.fire.vo.*;
import com.jyu.fire.vo.params.PageParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceMsgMapper deviceMsgMapper;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public Result insert(Device device) {

        int result = deviceMapper.insert(device);
        if (result == 1) {
            return Result.success("新增成功");
        }
        return Result.fail(ErrorCode.FAIL_INSERT.getMsg());
    }

    /**
     * 根据设备id查询该设备信息
     * @param id
     * @return
     */
    @Override
    public Result selectDeviceById(int id) {
        Device device = deviceMapper.selectById(id);
        if (device == null) {
            return Result.fail(ErrorCode.FAIL_SELECT.getMsg());
        }
        DeviceVo deviceVo = copy(device);
        return Result.success(deviceVo);
    }


    /**
     * 更新设备
     * @param device
     * @return
     */
    @Override
    public Result updateDevice(Device device) {
        int result = deviceMapper.updateById(device);
        if (result == 1) {
            return Result.success("更新成功");
        }
        return Result.fail(ErrorCode.FAIL_UPDATE.getMsg());
    }


    @Override
    public Result deleteById(int id) {
        int result = deviceMapper.deleteById(id);
        if (result == 1) {
            return Result.success("删除成功");
        }
        return Result.fail(ErrorCode.FAIL_DELETE.getMsg());
    }

    @Override
    public Result listDevice(PageParams pageParams) {
        /**
         * 分页查询device表
         */
        Page<Device> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        LambdaQueryWrapper<Device> queryWrapper = new LambdaQueryWrapper<>();
        //是否启用进行排序
        queryWrapper.orderByDesc(Device::getNum);
        Page<Device> devicePage = deviceMapper.selectPage(page, queryWrapper);
        List<Device> records = devicePage.getRecords();
        //需要将内容转换成自定义的vo对象进行返回
        List<DeviceVo> deviceVoList = copyList(records);
        //返回设备总数，设备在线数量、设备离线数量、设备未激活数量，当前是第几页，总页数
        //在数据库中分别查count(*) from device where status = {status}

//        return ListResult.success(deviceVoList);
        return null;
    }

    /**
     * 根据设备id查询出该设备的告警消息
     * @param id
     * @return
     */
    @Override
    public Result selectDeviceMsgById(int id) {
        DeviceMsg device_msg = deviceMsgMapper.selectByDeviceId(id);
        if (device_msg == null) {
            return Result.fail(ErrorCode.FAIL_SELECT.getMsg());
        }
        return Result.success(device_msg);
    }

    /**
     * 添加该设备的相关管理人员。待定
     * @param management_num
     * @param device_num
     * @param config_name
     * @return
     */
    @Override
    public Result addManagement(String management_num, String device_num, String config_name) {
        int result = deviceMapper.addDevice_Management(management_num, device_num, config_name);
        if (result == 1) {
            return Result.success("添加设备关联人成功");
        }
        return Result.fail(ErrorCode.FAIL_INSERT.getMsg());
    }

    /**
     * 根据设备id查询该设备相关管理人
     * @param id
     * @return
     */
    @Override
    public Result selectManagement(int id) {
        Management management = deviceMapper.selectManagementByDeviceId(id);
        //根据设备相关人查询出该设备人所属部门
        ManagementVo managementVo = copy(management);
        managementVo.setDepartment(departmentService.selectDepartmentNameById(management.getDepartmentId()));
        if (managementVo == null) {
            return Result.fail(ErrorCode.FAIL_SELECT.getMsg());
        }
        return Result.success(managementVo);
    }

    private List<DeviceVo> copyList(List<Device> records) {
        List<DeviceVo> deviceVoList = new ArrayList<>();
        for (Device record : records) {
            deviceVoList.add(copy(record));
        }
        return deviceVoList;
    }

    private DeviceVo copy(Device device) {
        DeviceVo deviceVo = new DeviceVo();
        //将device的内容copy到vo对象中
        BeanUtils.copyProperties(device,deviceVo);
        if (device.getType() == 0) {

        }
        return deviceVo;
    }

    private ManagementVo copy(Management management) {
        ManagementVo managementVo = new ManagementVo();
        BeanUtils.copyProperties(management,managementVo);
        return managementVo;
    }


}
