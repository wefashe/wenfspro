package com.example.demo.util.shiro;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

@Data
public class ShiroToken extends UsernamePasswordToken implements Serializable {
  private static final long serialVersionUID = -8682780447179431795L;

  private String passWorld;


  public ShiroToken(String userName, String passWorld, boolean rememberMe) {
    super(userName, passWorld, rememberMe);
    this.passWorld = passWorld;
  }



}
