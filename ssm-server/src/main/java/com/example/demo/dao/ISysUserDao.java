package com.example.demo.dao;

import com.example.demo.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wenfs
 * @since 2019-02-13
 */
@Repository
public interface ISysUserDao extends BaseMapper<SysUser> {

}
