package com.jyu.fire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jyu.fire.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DepartmentMapper extends BaseMapper<Department> {
    String selectDepartmentNameById(int id);

    int selectIdByName(String name);

}
