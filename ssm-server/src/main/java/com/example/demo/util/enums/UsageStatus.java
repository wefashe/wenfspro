package com.example.demo.util.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UsageStatus {

	DISABLE(0, "禁用"),
	ENABLE(1, "启用"),
	LOCKED(2,"锁定");

	@EnumValue
	private int value;
	@JsonValue
	private String desc;

}
