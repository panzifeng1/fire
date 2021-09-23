package com.jyu.fire.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@TableName(autoResultMap = true)
public class ManagementVo {
    private Integer id;
    private String num;
    private String name;
    private String note;
    //部门名字，根据部门id获得
    private String department;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Integer status;

}
