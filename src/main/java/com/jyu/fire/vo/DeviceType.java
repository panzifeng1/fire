package com.jyu.fire.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceType {
    private Integer type;
    //0，一般设备；1，海康威视；2，大华
    private String typeName;
}
