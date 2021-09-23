package com.jyu.fire.controller;

import com.jyu.fire.pojo.Device;
import com.jyu.fire.service.DeviceService;
import com.jyu.fire.vo.ErrorCode;
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

    @PostMapping("/addDevice")
    public Result insert(@RequestBody Device device) {
        return deviceService.insert(device);
    }

    @GetMapping("/seeDevice/{id}")
    public Result selectByNum(@PathVariable("id") int id) {
        return deviceService.selectDeviceById(id);
    }


    @GetMapping("/deleteDevice/{id}")
    public Result deleteByNum(@PathVariable("id") int id) {
        return deviceService.deleteById(id);
    }

    //查询设备列表
    @GetMapping("/listDevice")
    public Result listDevice(PageParams pageParams) {
        return deviceService.listDevice(pageParams);
    }


    @PostMapping("/updateDevice")
    public Result updateDevice(@RequestBody Device device) {
        return deviceService.updateDevice(device);
    }

    @GetMapping("/selectManagement/{id}")
    public Result selectManagement(@PathVariable("id") int id) {
        return deviceService.selectManagement(id);
    }



    @PostMapping("/addManagement")
    public Result addManagement(String management_num, String device_num, String config_name) {
        return deviceService.addManagement(management_num, device_num, config_name);
    }


    /**
     * 根据设备id查询出该设备相应的信息
     * @param id
     * @return
     */
    @GetMapping("/selectDeviceMsg/{id}")
    public Result selectDeviceMsg(@PathVariable("id") int id) {
        return deviceService.selectDeviceMsgById(id);
    }

}
