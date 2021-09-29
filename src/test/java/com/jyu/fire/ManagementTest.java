package com.jyu.fire;

import com.jyu.fire.controller.ManagementController;
import com.jyu.fire.service.ManagementService;
import com.jyu.fire.vo.ListManagementResult;
import com.jyu.fire.vo.ManagementVo;
import com.jyu.fire.vo.Result;
import com.jyu.fire.vo.params.PageParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FireApplication.class)
public class ManagementTest {

    @Autowired
    ManagementService managementService;


    @Test
    public void testAdd() {
        ManagementVo managementVo = new ManagementVo();
        managementVo.setName("张三");
        managementVo.setPhone("18666340204");
        managementVo.setNote("张三的信息");
        managementVo.setNum("102");
        managementVo.setDepartment("部门1");

        Result insert = managementService.insert(managementVo);
        System.out.println(insert);
    }

    @Test
    public void testUpdate() {
        ManagementVo managementVo = new ManagementVo();
        managementVo.setId(1);
        managementVo.setName("李四");
        managementVo.setPhone("18666340204");
        managementVo.setNote("李四的信息");
        managementVo.setNum("101");
        managementVo.setDepartment("部门1");

        Result update = managementService.update(managementVo);
        System.out.println(update);
    }

    @Test
    public void testDelete() {
        Result delete = managementService.delete(2);
        System.out.println(delete);
    }

    @Test
    public void selectOne() {
        ManagementVo managementVo = new ManagementVo();
        managementVo.setId(1);
        managementVo.setName("张三");
        managementVo.setPhone("18666340204");
        managementVo.setNote("李四的信息");
        managementVo.setNum("101");
        managementVo.setDepartment("部门1");
        Result select = managementService.select(managementVo);
        System.out.println(select);
    }

    @Test
    public void testList() {
        ListManagementResult result = managementService.listManagement(new PageParams());
        System.out.println(result);
    }

}
