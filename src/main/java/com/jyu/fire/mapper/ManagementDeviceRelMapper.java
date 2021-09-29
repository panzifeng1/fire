package com.jyu.fire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jyu.fire.pojo.ManagementDeviceRel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ManagementDeviceRelMapper extends BaseMapper<ManagementDeviceRel> {
    int insertManagement(Integer managementId,Integer deviceId,String msgCfg,String msgType);

    ManagementDeviceRel selectByPrimaryKey(Integer managementId,Integer deviceId);
}
