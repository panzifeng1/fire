package com.jyu.fire.vo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

@Data
@TableName(autoResultMap = true)
public class DeviceConfigVo {
    private Integer id;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONObject config;
}
