package com.example.demo.controller;

import com.example.demo.util.enums.MyExceptionEnums;
import com.example.demo.util.exception.MyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.kaptcha.Kaptcha;
import com.example.demo.entity.SysUser;
import com.example.demo.service.ISysPermService;
import com.example.demo.util.result.Result;
import com.example.demo.util.shiro.TokenManager;

@Controller
public class SysIndexController extends BaseController {

	@Autowired
	private Kaptcha kaptcha;
	@Autowired
	private ISysPermService permService;

	@GetMapping("/")
	public String index() {
		return "redirect:index";
	}

	@GetMapping("index")
	public String index(Model model) {
		SysUser user = TokenManager.getToken();
		model.addAttribute("user", user);
		model.addAttribute("menus", permService.getPerm(user.getUserId(), null, 1));
		return "index";
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}

	@GetMapping("/render")
	@ResponseBody
	public void render() {
		kaptcha.render();
	}

	@PostMapping("login")
	@ResponseBody
	public Result login(String userName, String passWord, String captcha, boolean rememberMe) {
		if(StringUtils.isBlank(userName)){
			throw new MyException(MyExceptionEnums.USERNAME_EMPTY);
		}
//		if (userName.length()<6||userName.length()>11){
//			throw new MyException(MyExceptionEnums.USERNAME_NUM_ERROR);
//		}
		if (StringUtils.isBlank(passWord)){
			throw new MyException(MyExceptionEnums.PASSWORD_EMPTY);
		}
//		if (passWord.length()<11||passWord.length()>20){
//			throw new MyException(MyExceptionEnums.PASSWORD_NUM_ERROR);
//		}
		if (StringUtils.isBlank(captcha)){
			throw new MyException(MyExceptionEnums.KAPTCHA_EMPTY);
		}
		if (captcha.length()!=4){
			throw new MyException(MyExceptionEnums.KAPTCHA_NUM_FOUR);
		}
		kaptcha.validate(captcha.trim(), 60);
		SysUser user = TokenManager.login(userName.trim(), passWord.trim(), rememberMe);
		return new Result(user);
	}

}
