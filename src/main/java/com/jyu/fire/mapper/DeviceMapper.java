package com.jyu.fire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jyu.fire.pojo.Device;
import com.jyu.fire.pojo.Management;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DeviceMapper extends BaseMapper<Device> {

    Device selectById(int id);

    int deleteById(int id);

    Management selectManagementByDeviceId(int id);

    int addDevice_Management(String management_num,String device_num,String config_name);
}
