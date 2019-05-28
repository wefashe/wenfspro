package com.example.demo.service.impl;

import com.example.demo.entity.SysDept;
import com.example.demo.dao.ISysDeptDao;
import com.example.demo.service.ISysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author wenfs
 * @since 2019-02-13
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<ISysDeptDao, SysDept> implements ISysDeptService {

  @Autowired
  private ISysDeptDao deptDao;

}
