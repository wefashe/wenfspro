package com.example.demo.service;

import com.example.demo.entity.SysPerm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author wenfs
 * @since 2019-02-13
 */
public interface ISysPermService extends IService<SysPerm> {

    List<SysPerm> getPerm(String userId, String permName, Integer permType);

    void editPerm(SysPerm perm);
}
