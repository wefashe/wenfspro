package com.example.demo.dao;

import com.example.demo.entity.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author wenfs
 * @since 2019-02-13
 */
@Repository
public interface ISysDeptDao extends BaseMapper<SysDept> {

}
