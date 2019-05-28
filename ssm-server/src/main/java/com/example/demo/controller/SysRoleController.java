package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author wenfs
 * @since 2019-02-13
 */
@Controller
@RequestMapping("role")
public class SysRoleController {

	@GetMapping("/")
	public String index() {
		return "system/role/role";
	}

}
