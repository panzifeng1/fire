package com.jyu.fire.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private int code;
    private String message;
    private Object data;


    public static Result success(Object data) {
        return new Result(200,"success",data);
    }

    public static Result fail(String message) {
        return new Result(400,message,null);
    }
}
