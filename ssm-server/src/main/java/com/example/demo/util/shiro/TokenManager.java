package com.example.demo.util.shiro;

import com.example.demo.util.enums.MyExceptionEnums;
import com.example.demo.util.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.example.demo.entity.SysUser;

@Slf4j
public class TokenManager {

  public static SysUser getToken() {
    SysUser token = (SysUser) SecurityUtils.getSubject().getPrincipal();
    return token;
  }

  public static SysUser login(String userName, String passWord, boolean rememberMe) {
    ShiroToken token = new ShiroToken(userName, passWord, rememberMe);
    Subject currentUser = SecurityUtils.getSubject();
    currentUser.login(token);
    //验证是否登录成功
    if (currentUser.isAuthenticated()){
      log.debug("用户【"+userName+"】登陆成功！");
      //这里可以进行一些认证通过后的一些系统参数初始化操作
    }
    return getToken();
  }

  //  DisabledAccountException （禁用的帐号）
  //  LockedAccountException （锁定的帐号）
  //  UnknownAccountException（错误的帐号）
  //  ExcessiveAttemptsException（登录失败次数过多）
  //  IncorrectCredentialsException （错误的凭证）
  //  ExpiredCredentialsException （过期的凭证）

}