package com.example.demo.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常枚举
 *
 * @author Administrator
 */
@Getter
@AllArgsConstructor
public enum MyExceptionEnums {
	
	// 系统
	REQUEST_METHOD_NO_FOUND(1001,"方法不存在！请检查请求路径是否正确！"),
	//用户
	USER_NOT_EXISTS(101, "用户不存在"),
	USER_STOP_USING(102, "用户未启用"),
	USER_DUPLICATE(103, "用户有多个"),
	USERNAME_EMPTY(104, "用户名为空"),
	PASSWORD_ERROR(105, "密码错误"),
	PASSWORD_EMPTY(106, "密码为空"),
	USERNAME_NUM_ERROR(107, "用户名个数为6到11位之间"),
	PASSWORD_NUM_ERROR(108, "密码个数为11到20位之间"),

	//验证码
	KAPTCHA_RENDERFAIL(2101,"验证码渲染失败"),
	KAPTCHA_NOTFOUND(2102,"验证码未找到"),
	KAPTCHA_INCORRECT(2103, "验证码不正确"),
	KAPTCHA_TIMEOUT(2104, "验证码过期"),
	KAPTCHA_EMPTY(2105, "验证码为空"),
	KAPTCHA_NUM_FOUR(2016,"验证码必须4位"),

	//登录
	NOT_LOGIN(201,"未登录"),


	//权限
	NOT_AUTH(301,"用户没有该权限"),


	//授权
	AUTHORIZE_FAILED(20000, "tokenId无效"),
	TOKENID_EMPTY(20001, "tokenId为空"),
	TOKENID_NOT_AUTH(20002, "tokenId未授权"),
	TOKENID_DUPLICATE(20003, "tokenId存在多条，数据异常"),
	TOKENID_EXPIRE(20004, "tokenId过期"),
	TOKENID_USERID_EMPTY(20005, "当前tokenId的授权用户不存在"),

	//共用
	RGCODE_EMPTY(30000, "区划代码不能为空"),
	SETYEAR_EMPTY(30001, "年度不能为空"),

	//基础要素
	ELEMENT_ELE_CODE_EMPTY(40000, "要素编码不能为空"),
	ELEMENT_NOT_EXISTS(40001, "要素不存在"),
	ELEMENT_DUPLICATE(40002, "要素重复，请联系管理员"),
	ELEMENT_SOURECE_NOT_EXISTS(40003, "要素源数据不存在，请联系管理员"),
	ELEMENT_ELE_COLNAME_EMPTY(40004, "元数据字段名不能为空"),

	//分级授权
	LEVEL_AUTH_UUM_MANAGER_NOT_EXISTS(50000, "当前年度的内置管理员不存在"),
	LEVEL_AUTH_UUM_MANAGER_DEL_EMPTY(50001, "没有需要删除的管理员"),

	//未知异常
	UNKNOWN_EXCEPTION(-9999, "系统出现未知异常");



	/**
	 * 异常编码
	 */
	private int errorCode;

	/**
	 * 异常信息
	 */
	private String errorMsg;


}
