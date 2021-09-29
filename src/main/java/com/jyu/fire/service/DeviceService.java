package com.jyu.fire.service;

import com.jyu.fire.pojo.Device;
import com.jyu.fire.vo.ListResult;
import com.jyu.fire.vo.Result;
import com.jyu.fire.vo.params.PageParams;

public interface DeviceService {
    //新增设备
    Result insert(Device device);

    /**
     * 根据设备id查询设备
     */
    Result selectDeviceById(int id);


    //更新设备
    Result updateDevice(Device device);

    //删除设备
    Result deleteById(int id);

    //设备列表
    ListResult listDevice(PageParams pageParams);

    //根据设备id查询当前设备的告警信息
    Result selectDeviceMsgById(int id);

    //新增当前设备的关联人
    Result addManagement(String management_num,String device_num,String config_name);

    //根据设备号查询当前管理设备的人
    Result selectManagement(int id);

    //查询所有部门id和部门名称
    Result selectDepartments();
}
