package com.example.demo.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {

  ADMIN(0, "普通管理员"),
  ROOT(1, "超级管理员"),
  COMPANY_SUPPER(2, "企业超级管理员"),
  COMPANY_ADMIN(3, "企业管理员"),
  COMPANY_MEMBER(4, "企业成员");

  private int value;
  private String desc;

}
