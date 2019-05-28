package com.example.demo.service.impl;

import com.example.demo.entity.SysUser;
import com.example.demo.dao.ISysUserDao;
import com.example.demo.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wenfs
 * @since 2019-02-13
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<ISysUserDao, SysUser> implements ISysUserService {

  @Autowired
  private ISysUserDao userDao;

}
