package com.jyu.fire.controller;

import com.jyu.fire.service.ManagementService;
import com.jyu.fire.vo.ListManagementResult;
import com.jyu.fire.vo.ManagementVo;
import com.jyu.fire.vo.Result;
import com.jyu.fire.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
public class ManagementController {

    @Autowired
    ManagementService managementService;

    @RequestMapping("/list")
    public ListManagementResult list(PageParams pageParams) {
        return managementService.listManagement(pageParams);
    }

    @RequestMapping("/select")
    public Result select(ManagementVo managementVo) {
        return managementService.select(managementVo);
    }

    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable("id") int id) {
        return managementService.delete(id);
    }

    @RequestMapping("/insert")
    public Result insert(ManagementVo managementVo) {
        return managementService.insert(managementVo);
    }

    @RequestMapping("/update")
    public Result update(ManagementVo managementVo) {
        return managementService.update(managementVo);
    }
}
