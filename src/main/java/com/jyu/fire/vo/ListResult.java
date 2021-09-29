package com.jyu.fire.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListResult {
    private boolean success;
    private int code;
    private String message;
    private Object data;
    private long total;
    private long current;
    private long pageCount;
    private Integer onLine;
    private Integer offLine;
    private Integer notActive;
    public static ListResult success(Object data, long total, long current, long pageCount, Integer onLine, Integer offLine, Integer notActive) {
        return new ListResult(true,200,"success",data,total,current,pageCount,onLine,offLine,notActive);
    }

}
