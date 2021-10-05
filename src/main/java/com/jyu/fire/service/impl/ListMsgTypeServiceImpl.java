package com.jyu.fire.service.impl;

import com.jyu.fire.pojo.MsgType;
import com.jyu.fire.service.ListMsgTypeService;
import com.jyu.fire.vo.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListMsgTypeServiceImpl implements ListMsgTypeService {

    @Override
    public Result listMsgType() {
        List<MsgType> list = new ArrayList<>();
        list.add(new MsgType(10,"设备离线"));
        list.add(new MsgType(11,"设备上线"));
        list.add(new MsgType(20,"火灾告警"));
        list.add(new MsgType(21,"告警解除"));
        return Result.success(list);
    }
}
