package com.jyu.fire.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagementDeviceRel {
    private Integer managementId;
    private Integer deviceId;
    private List<MsgCfg> msgCfg;
    private List<MsgType> msgType;
}
