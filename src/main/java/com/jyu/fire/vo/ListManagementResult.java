package com.jyu.fire.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListManagementResult {
    private boolean success;
    private int code;
    private String message;
    private Object data;
    private long total;
    private long current;
    private long pageCount;

    public static ListManagementResult success(Object data,long total,long current,long pageCount) {
        return new ListManagementResult(true,200,"success",data,total,current,pageCount);
    }
}
