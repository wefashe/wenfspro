package com.example.demo.util.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 自定义一个对象用来封装token
 *
 * @author wenfs
 * @create 2018-09-30 14:14
 **/

public class JwtToken implements AuthenticationToken {
  /**
   * 密钥
   */
  private String token;

  public JwtToken(String token) {
    this.token = token;
  }

  @Override
  public Object getPrincipal() {
    return token;
  }

  @Override
  public Object getCredentials() {
    return token;
  }
}
