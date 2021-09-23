package com.jyu.fire.service.impl;

import com.jyu.fire.mapper.DepartmentMapper;
import com.jyu.fire.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public String selectDepartmentNameById(Integer id) {
        return departmentMapper.selectDepartmentNameById(id);
    }
}
