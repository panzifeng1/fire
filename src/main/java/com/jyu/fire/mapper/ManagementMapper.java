package com.jyu.fire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jyu.fire.pojo.Management;
import com.jyu.fire.vo.ManagementVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


@Mapper
@Repository
public interface ManagementMapper extends BaseMapper<Management> {
    String selectPhoneByDeviceId(String device_id);

    Management select(Management management);

    Management selectByNum(int num);
}
