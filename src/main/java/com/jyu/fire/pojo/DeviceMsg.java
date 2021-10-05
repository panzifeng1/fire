package com.jyu.fire.pojo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(autoResultMap = true)
public class DeviceMsg {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer deviceId;
    private Integer type;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONObject msg;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public DeviceMsg(Integer deviceId,Integer type,String msg) {
        this.deviceId = deviceId;
        this.type = type;
        Object o = JSONObject.toJSON(msg);
        this.msg = (JSONObject) o;
    }
}
