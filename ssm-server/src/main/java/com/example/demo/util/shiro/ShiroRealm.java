package com.example.demo.util.shiro;

import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.dao.ISysPermDao;
import com.example.demo.dao.ISysRoleDao;
import com.example.demo.dao.ISysUserDao;
import com.example.demo.entity.SysPerm;
import com.example.demo.entity.SysRole;
import com.example.demo.entity.SysUser;
import com.example.demo.util.enums.UsageStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

  @Autowired
  ISysUserDao userDao;

  @Autowired
  ISysPermDao permDao;

  @Autowired
  ISysRoleDao roleDao;

  /**
   *  shiro登录认证
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
    // 获取用户输入信息
    String userName = (String) authcToken.getPrincipal();

    LambdaQueryWrapper<SysUser> lambda = new LambdaQueryWrapper<>();
    lambda.eq(SysUser::getUserName, userName);
    SysUser user = userDao.selectOne(lambda);
    //账户不存在
    if (null == user) {
      throw new UnknownAccountException();
    }
    //账户停用
    if (UsageStatus.DISABLE.equals(user.getUserStatus())) {
      throw new DisabledAccountException();
    }
    //更新登录时间
    userDao.updateById(new SysUser().setUserId(user.getUserId()).setLastLoginTime(System.currentTimeMillis() + ""));

    //盐值  一般是用户名
    ByteSource credentialsSalt = ByteSource.Util.bytes(user.getPassSalt());
    return new SimpleAuthenticationInfo(user, user.getUserPass(), credentialsSalt, getName());
  }

  /** 
    * shiro权限认证
    */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    SysUser user = (SysUser) principals.getPrimaryPrincipal();
    log.info("{}开始授予权限", user.getUserName());
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    // 获取用户角色集
    List<SysRole> roles = roleDao.getRole(user.getUserId());
    Set<String> roleSet = roles.stream().map(SysRole::getRoleId).collect(Collectors.toSet());
    simpleAuthorizationInfo.setRoles(roleSet);
    log.info("{}角色为{}", user.getUserName(),roleSet);
    //获取用户权限集
    List<SysPerm> perms = permDao.getPerm(user.getUserId(),null,null);
    Set<String> permSet = perms.stream().map(SysPerm::getPermId).collect(Collectors.toSet());
    simpleAuthorizationInfo.setStringPermissions(permSet);
    log.info("{}权限有{}", user.getUserName(),permSet);
    return simpleAuthorizationInfo;
  }

  /**
   * 指定principalCollection 清除
   */
  @Override
  public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
    SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
    super.clearCachedAuthorizationInfo(principals);
  }

  /**
   * 清空当前用户权限信息
   */
  public void clearCachedAuthorizationInfo() {
    PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
    SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
    super.clearCachedAuthorizationInfo(principals);
  }

}
