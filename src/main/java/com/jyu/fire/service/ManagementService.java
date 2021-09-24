package com.jyu.fire.service;

import com.jyu.fire.pojo.Management;
import com.jyu.fire.vo.ManagementVo;
import com.jyu.fire.vo.Result;
import com.jyu.fire.vo.params.PageParams;

import java.util.List;

public interface ManagementService {
    /**
     * 获取人员列表
     */
    Result listManagement(PageParams pageParams);

    /**
     * 查询人员信息
     */
    Result select(ManagementVo managementVo);

    /**
     * 删除人员信息
     */
    Result delete(int id);

    /**
     * 添加人员
     */
    Result insert(ManagementVo managementVo);

    /**
     * 更新人员信息
     */
    Result update(ManagementVo managementVo);
}
