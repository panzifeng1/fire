package com.jyu.fire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jyu.fire.pojo.MsgConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MsgConfigMapper extends BaseMapper<MsgConfig> {
    MsgConfig selectBy(MsgConfig msgConfig);
}
