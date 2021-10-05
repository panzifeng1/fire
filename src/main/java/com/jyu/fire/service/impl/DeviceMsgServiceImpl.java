package com.jyu.fire.service.impl;

import com.jyu.fire.mapper.DeviceMsgMapper;
import com.jyu.fire.pojo.DeviceMsg;
import com.jyu.fire.service.DeviceMsgService;
import com.jyu.fire.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceMsgServiceImpl implements DeviceMsgService {
    @Autowired
    DeviceMsgMapper deviceMsgMapper;
    @Override
    public Result insert(DeviceMsg deviceMsg) {
        int insert = deviceMsgMapper.insert(deviceMsg);
        if (insert == 1) {
            return Result.success("插入消息告警成功");
        }
        return Result.fail("插入消息告警失败");
    }
}
