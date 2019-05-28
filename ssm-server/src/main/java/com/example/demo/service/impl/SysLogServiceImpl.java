package com.example.demo.service.impl;

import com.example.demo.entity.SysLog;
import com.example.demo.dao.ISysLogDao;
import com.example.demo.service.ISysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author wenfs
 * @since 2019-02-13
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<ISysLogDao, SysLog> implements ISysLogService {

}
