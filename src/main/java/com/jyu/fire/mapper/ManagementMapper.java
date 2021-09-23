package com.jyu.fire.mapper;

import com.jyu.fire.pojo.Management;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


@Mapper
@Repository
public interface ManagementMapper {
    String selectPhoneByDeviceId(String device_id);
}
