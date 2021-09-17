package com.jyu.fire.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginLog {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long user_id;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date create_time;

    private String ip;

    private String user_agent;
}
