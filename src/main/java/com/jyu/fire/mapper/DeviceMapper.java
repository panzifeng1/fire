package com.jyu.fire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jyu.fire.pojo.Device;
import com.jyu.fire.pojo.Management;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Mapper
@Repository
public interface DeviceMapper extends BaseMapper<Device> {



    int deleteById(int id);

    Management selectManagementByDeviceId(int id);

    int addDevice_Management(String management_num,String device_num,String config_name);

    int selectCountByStatus(int status);

    int selectStatusById(Integer id);

    Device selectByNum(String num);

    Device selectLastOne();


}
