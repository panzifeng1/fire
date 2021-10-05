package com.jyu.fire.controller;

import com.jyu.fire.pojo.Device;
import com.jyu.fire.pojo.ManagementDeviceRel;
import com.jyu.fire.service.DeviceService;
import com.jyu.fire.service.ListMsgTypeService;
import com.jyu.fire.service.ManagementService;
import com.jyu.fire.vo.DeviceConfigVo;
import com.jyu.fire.vo.ErrorCode;
import com.jyu.fire.vo.ListResult;
import com.jyu.fire.vo.Result;
import com.jyu.fire.vo.params.PageParams;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/device",produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private ListMsgTypeService listMsgTypeService;

    @RequestMapping("/addDevice")
    public Result insert(@RequestBody Device device) {
        return deviceService.insert(device);
    }

    @RequestMapping("/seeDevice/{id}")
    public Result selectByNum(@PathVariable("id") int id) {
        return deviceService.selectDeviceById(id);
    }


    @RequestMapping("/deleteDevice/{id}")
    public Result delete(@PathVariable("id") int id) {
        return deviceService.deleteById(id);
    }

    //查询设备列表
    @RequestMapping("/listDevice")
    public ListResult listDevice(PageParams pageParams) {
        return deviceService.listDevice(pageParams);
    }


    @RequestMapping("/updateDevice")
    public Result updateDevice(Device device) {
        return deviceService.updateDevice(device);
    }

    @RequestMapping("/selectManagement/{id}")
    public Result selectManagement(@PathVariable("id") int id) {
        return deviceService.selectManagement(id);
    }



    @RequestMapping("/addManagement")
    public Result addManagement(@RequestBody ManagementDeviceRel managementDeviceRel) {
        return deviceService.addManagement(managementDeviceRel);
    }


    /**
     * 查询设备告警信息
     */
    @RequestMapping("/selectDeviceMsg/{id}")
    public Result selectDeviceMsg(@PathVariable("id") int id) {
        return deviceService.selectDeviceMsgById(id);
    }

    @RequestMapping("/selectDepartments")
    public Result selectDepartments() {
        return deviceService.selectDepartments();
    }

    /**
     * 查询出关联人列表
     */
    @RequestMapping("/listManagement")
    public Result listManagement() {
        return managementService.listManagementForDevice();
    }

    /**
     * 通知方式列表
     */
    @RequestMapping("/listInformType")
    public Result listInformType() {
        return deviceService.MsgConfigList();
    }

    /**
     * 消息类型列表
     */
    @RequestMapping("/listMsgType")
    public Result listMsgType() {
        return listMsgTypeService.listMsgType();
    }

    /**
     * 查看设备配置
     */
    @RequestMapping("/selectConfig/{id}")
    public Result selectConfig(@PathVariable("id") int id) {
        return deviceService.selectConfig(id);
    }


    @RequestMapping("/updateConfig")
    public Result updateConfig(Device device) {
        return deviceService.updateDevice(device);
    }
}
