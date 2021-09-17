package com.jyu.fire.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mqtt {
    private String deviceId;
    private String msg;
    private String time;
    private String msgType;
}
