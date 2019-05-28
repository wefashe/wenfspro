package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.ISysPermDao;
import com.example.demo.entity.SysPerm;
import com.example.demo.service.ISysPermService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author wenfs
 * @since 2019-02-13
 */
@Service
public class SysPermServiceImpl extends ServiceImpl<ISysPermDao, SysPerm> implements ISysPermService {

  @Autowired
  private ISysPermDao permDao;

  @Override
  public List<SysPerm> getPerm(String userId, String permName, Integer permType) {
    return permDao.getPerm(userId,permName,permType);
  }

  @Override
  public void editPerm(SysPerm perm) {

    if(StringUtils.isBlank(perm.getPermId())){
//      perm.
    }



  }


}
