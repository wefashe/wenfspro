package com.example.demo.util.result;

import java.io.Serializable;

import com.example.demo.util.enums.MyExceptionEnums;
import com.example.demo.util.exception.MyException;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * @Author: Wenfs
 * @Email: wenfs@yonyou.com
 * @Date: 2018/11/15
 * @Description: 返回对象包装类(带泛型)
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

	private static final long serialVersionUID = -5513079302223650790L;

	public static final int SUCCESS = 0;

	/**
	 * 接口返回码, 0表示成功, 其他看对应的定义 晓风轻推荐的做法是: 0 : 成功 >0 : 表示已知的异常(例如提示错误等, 需要调用地方单独处理) <0
	 * : 表示未知的异常(不需要单独处理, 调用方统一处理)
	 */
	private int resultCode = SUCCESS;

	/**
	 * 返回的信息(主要出错的时候使用)
	 */
	private String resultMsg;

	/**
	 * 返回的数据(主要成功的时候使用)
	 */
	private T data;

	/**
	 * 操作成功
	 */
	public Result() {
	}

	/**
	 * 操作成功
	 * 
	 * @param data 返回数据
	 */
	public Result(T data) {
		this.data = data;
	}

	/**
	 * 操作失败-已知异常
	 * 
	 */
	public Result(int resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}
	
	
	/**
	 * 操作失败-已知异常
	 * 
	 */
	public Result(MyException e) {
		this(e.getErrorCode(), e.getLocalizedMessage());
	}
	

	/**
	 * 操作失败-已知异常
	 * 
	 */
	public Result(MyExceptionEnums exceptionEnums) {
		this(exceptionEnums.getErrorCode(), exceptionEnums.getErrorMsg());
	}
	

	/**
	 * 操作失败-未知异常
	 * 
	 * @param e 失败异常
	 */
	public Result(Throwable e) {
//		this(MyExceptionEnums.UNKNOWN_EXCEPTION);
		this(MyExceptionEnums.UNKNOWN_EXCEPTION.getErrorCode(), e.getLocalizedMessage());
	}
}
