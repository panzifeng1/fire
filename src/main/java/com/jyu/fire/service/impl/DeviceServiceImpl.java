package com.jyu.fire.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyu.fire.mapper.*;
import com.jyu.fire.pojo.*;
import com.jyu.fire.service.DepartmentService;
import com.jyu.fire.service.DeviceService;
import com.jyu.fire.vo.*;
import com.jyu.fire.vo.params.PageParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceMsgMapper deviceMsgMapper;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private ManagementDeviceRelMapper managementDeviceRelMapper;
    @Autowired
    private MsgConfigMapper msgConfigMapper;

    @Override
    public Result insert(Device device) {
        //判断该设备号在数据库中是否已经存在
        if (existDevice(device.getNum())) {
            return Result.fail("新增失败，数据库中已存在该设备");
        }
        //新增的设备默认为未激活状态
        device.setStatus(0);
        int result = deviceMapper.insert(device);
        if (result == 1) {
            DeviceVo deviceVo = copy(deviceMapper.selectLastOne());
            return Result.success(deviceVo);
        }
        return Result.fail("新增失败");
    }

    /**
     * 根据设备id查询该设备信息
     * @param id
     * @return
     */
    @Override
    public Result selectDeviceById(int id) {
        Device device = deviceMapper.selectById(id);
        if (device != null) {
            DeviceVo deviceVo = copy(device);
            return Result.success(deviceVo);
        }
        return Result.fail("查找不到该设备");
    }


    /**
     * 更新设备
     * @param device
     * @return
     */
    @Override
    public Result updateDevice(Device device) {
        //判断该设备号在数据库中是否已经存在
        if (device.getNum()!="" && device.getNum()!=" ") {
            if (existDevice(device.getNum())) {
                return Result.fail("更新失败，设备号不能重复");
            }
        }
        //更新最后在线时间
        int oldStatus = deviceMapper.selectStatusById(device.getId());
        int newStatus = device.getStatus();
        if (oldStatus == 2 && newStatus == 1) {
            //从在线转换为离线，更新最后在线时间
            LocalDateTime now = LocalDateTime.now();
            device.setLastOnlineTime(now);
        }
        int result = deviceMapper.updateById(device);
        if (result == 1) {
            Device device1 = deviceMapper.selectById(device.getId());
            DeviceVo deviceVo = copy(device1);
            return Result.success(deviceVo);
        }
        return Result.fail("更新失败");
    }


    @Override
    public Result deleteById(int id) {
        int result = deviceMapper.deleteById(id);
        if (result == 1) {
            return Result.success(null);
        }
        return Result.fail("删除失败");
    }

    /**
     * 分页查询device表
     */
    @Override
    public ListResult listDevice(PageParams pageParams) {
        Page<Device> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        LambdaQueryWrapper<Device> queryWrapper = new LambdaQueryWrapper<>();
        //根据设备号进行排序
        queryWrapper.orderByDesc(Device::getNum);
        Page<Device> devicePage = deviceMapper.selectPage(page, queryWrapper);
        List<Device> records = devicePage.getRecords();
        //需要将内容转换成自定义的vo对象进行返回
        List<DeviceVo> deviceVoList = copyList(records);
        //设备总数
        long total = devicePage.getTotal();
        //总页数
        long pages = devicePage.getPages();
        //当前页数
        long current = devicePage.getCurrent();
        int notActive = deviceMapper.selectCountByStatus(0);
        int offLine = deviceMapper.selectCountByStatus(1);
        int onLine = deviceMapper.selectCountByStatus(2);
        return ListResult.success(deviceVoList,total,current,pages,onLine,offLine,notActive);
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
            return Result.fail("查询失败");
        }
        return Result.success(device_msg);
    }

    /**
     * 添加该设备的相关管理人员。待定
     */
    @Override
    public Result addManagement(ManagementDeviceRel managementDeviceRel) {
        Integer managementId = managementDeviceRel.getManagementId();
        Integer deviceId = managementDeviceRel.getDeviceId();
        List msgCfg = managementDeviceRel.getMsgCfg();
        String s = JSON.toJSONString(msgCfg);
        List msgType = managementDeviceRel.getMsgType();
        String s1 = JSON.toJSONString(msgType);

        //插入之前先在数据库中查询该设备是否已经关联了某个关联人
        ManagementDeviceRel selectByPrimaryKey = managementDeviceRelMapper.selectByPrimaryKey(managementId, deviceId);
        if (selectByPrimaryKey != null) {
            return Result.fail("添加失败，该设备已经存在相关关联人");
        }

        int i = managementDeviceRelMapper.insertManagement(managementId, deviceId, s, s1);
        if (i == 1) {
            return Result.success(null);
        }
        return Result.fail("添加设备关联人失败");
    }

    /**
     * 根据设备id查询该设备相关管理人
     * @param id
     * @return
     */
    @Override
    public Result selectManagement(int id) {
        Management management = deviceMapper.selectManagementByDeviceId(id);
        if (management == null) {
            return Result.fail("查询不到设备相关关联人");
        }
        //根据设备相关人查询出该设备人所属部门
        ManagementVo managementVo = copy(management);
        managementVo.setDepartment(departmentService.selectDepartmentNameById(management.getDepartmentId()));
        if (managementVo == null) {
            return Result.fail("查询不到设备相关关联人");
        }
        return Result.success(managementVo);
    }

    @Override
    public Result selectDepartments() {
        List<DepartmentNameAndId> departmentNameAndIds = departmentMapper.selectAll();
        return Result.success(departmentNameAndIds);
    }

    @Override
    public Result MsgConfigList() {
        List<MsgConfig> msgConfigs = msgConfigMapper.selectList(null);
        List<ListMsgConfigVo> listMsgConfigVos = copyMsgList(msgConfigs);
        return Result.success(listMsgConfigVos);
    }

    @Override
    public Result selectConfig(int id) {
        Device device = deviceMapper.selectById(id);
        DeviceConfigVo deviceConfigVo = copyToConfig(device);
        if (deviceConfigVo != null) {
            return Result.success(deviceConfigVo);
        }
        return Result.fail("找不到该设备的相关配置信息");
    }



    private DeviceConfigVo copyToConfig(Device device) {
        DeviceConfigVo deviceConfigVo = new DeviceConfigVo();
        BeanUtils.copyProperties(device,deviceConfigVo);
        return deviceConfigVo;
    }


    /**
     * 判断数据库中是否已经存在该设备，存在返回true，不存在返回false
     */
    private boolean existDevice(String num) {
        Device device = deviceMapper.selectByNum(num);
        if (device == null) return false;
        return true;
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
        if (device.getDepartmentId() != null) {
            String departmentName = departmentMapper.selectDepartmentNameById(device.getDepartmentId());
            deviceVo.setDepartment(departmentName);
        }

        //设备类型
        DeviceType deviceType = new DeviceType();
        Integer type = device.getType();
        if (type == 0) {
            deviceType.setType(type);
            deviceType.setTypeName("一般设备");
        }
        if (type == 1) {
            deviceType.setType(type);
            deviceType.setTypeName("海康威视");
        }
        if (type == 2) {
            deviceType.setType(type);
            deviceType.setTypeName("大华");
        }
        deviceVo.setDeviceType(deviceType);
        return deviceVo;
    }

    private ManagementVo copy(Management management) {
        ManagementVo managementVo = new ManagementVo();
        BeanUtils.copyProperties(management,managementVo);
        return managementVo;
    }

    private List<ListMsgConfigVo> copyMsgList(List<MsgConfig> records) {
        List<ListMsgConfigVo> msgConfigs = new ArrayList<>();
        for (MsgConfig record : records) {
            msgConfigs.add(copy(record));
        }
        return msgConfigs;
    }

    private ListMsgConfigVo copy(MsgConfig msgConfig) {
        ListMsgConfigVo listMsgConfigVo = new ListMsgConfigVo();
        BeanUtils.copyProperties(msgConfig,listMsgConfigVo);
        return listMsgConfigVo;
    }
}
