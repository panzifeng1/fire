package com.jyu.fire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jyu.fire.pojo.MsgConfig;

public interface MsgConfigMapper extends BaseMapper<MsgConfig> {
    MsgConfig selectBy(MsgConfig msgConfig);
}
