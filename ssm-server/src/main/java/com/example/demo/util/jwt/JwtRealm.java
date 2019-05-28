package com.example.demo.util.jwt;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.dao.ISysPermDao;
import com.example.demo.dao.ISysRoleDao;
import com.example.demo.dao.ISysUserDao;
import com.example.demo.entity.SysPerm;
import com.example.demo.entity.SysRole;
import com.example.demo.entity.SysUser;
import com.example.demo.util.enums.UsageStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class JwtRealm extends AuthorizingRealm {

  @Autowired ISysUserDao userDao;

  @Autowired ISysPermDao permDao;

  @Autowired ISysRoleDao roleDao;

  /**
   * 必须重写此方法，不然shiro会报错
   * 使用JWT替代原生Token
   * @param token
   * @return
   */
  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof JwtToken;
  }

  /**
   * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可
   *
   * @param authcToken
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

    String token = (String) authcToken.getPrincipal();
    String userName = JwtUtil.getUserName(token);

    log.info("{}开始登录认证", authcToken.getPrincipal());
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
    log.info("{}登录认证通过", authcToken.getPrincipal());

    return new SimpleAuthenticationInfo(user, token, getName());
  }

  /**
   * 只有当需要检测用户权限的时候才会调用此方法
   *
   * @param principals
   * @return
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

    SysUser user = (SysUser) principals.getPrimaryPrincipal();
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    // 获取用户角色集
    List<SysRole> roles = roleDao.getRole(user.getUserId());
    Set<String> roleSet = roles.stream().map(SysRole::getRoleId).collect(Collectors.toSet());
    simpleAuthorizationInfo.setRoles(roleSet);

    //获取用户权限集
    List<SysPerm> perms = permDao.getPerm(user.getUserId(),null,null);
    Set<String> permSet = perms.stream().map(SysPerm::getPermId).collect(Collectors.toSet());

    simpleAuthorizationInfo.setStringPermissions(permSet);
    return simpleAuthorizationInfo;
  }
}
