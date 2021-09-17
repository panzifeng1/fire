package com.jyu.fire.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListResult {
    private boolean success;
    private int code;
    private String msg;
    private Object data;
    private Integer total;
    private Integer page;
    private Integer pageCount;
    private Integer onLine;
    private Integer offLine;
    private Integer notActive;
    public static ListResult success(Object data,Integer total,Integer page,Integer pageCount,Integer onLine,Integer offLine,Integer notActive) {
        return new ListResult(true,200,"success",data,total,page,pageCount,onLine,offLine,notActive);
    }

}
