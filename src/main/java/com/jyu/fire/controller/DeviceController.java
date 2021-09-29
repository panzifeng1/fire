package com.jyu.fire.controller;

import com.jyu.fire.pojo.Device;
import com.jyu.fire.service.DeviceService;
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
    public Result addManagement(String management_num, String device_num, String config_name) {
        return deviceService.addManagement(management_num, device_num, config_name);
    }


    /**
     * 根据设备id查询出该设备相应的信息
     * @param id
     * @return
     */
    @RequestMapping("/selectDeviceMsg/{id}")
    public Result selectDeviceMsg(@PathVariable("id") int id) {
        return deviceService.selectDeviceMsgById(id);
    }

    @RequestMapping("/selectDepartments")
    public Result selectDepartments() {
        return deviceService.selectDepartments();
    }

}
