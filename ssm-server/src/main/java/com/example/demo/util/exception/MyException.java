package com.example.demo.util.exception;

import com.example.demo.util.enums.MyExceptionEnums;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 * @author wenfs
 */
@Getter
@Setter
public class MyException  extends RuntimeException{
  private static final long serialVersionUID = -6578894526492497688L;

  private Integer errorCode;

  public MyException(MyExceptionEnums enums) {
    super(enums.getErrorMsg());
    this.errorCode = enums.getErrorCode();
  }

  public MyException(int errorCode, String errorMsg) {
    super(errorMsg);
    this.errorCode = errorCode;
  }
}
