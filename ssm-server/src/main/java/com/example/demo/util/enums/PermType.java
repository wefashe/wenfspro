package com.example.demo.util.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermType {

	MENU(1, "菜单"),
	BUTTON(2, "按钮"),
	OTHER(3, "其他");

	@EnumValue
	private int value;
	@JsonValue
	private String name;

}
