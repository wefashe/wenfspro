package com.example.demo.controller;

import com.example.demo.entity.SysPerm;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.SysUser;
import com.example.demo.service.ISysPermService;
import com.example.demo.util.result.Result;
import com.example.demo.util.shiro.TokenManager;

import io.swagger.annotations.Api;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author wenfs
 * @since 2019-02-13
 */
@Api(value = "权限接口", tags = "权限接口")
@Controller
@RequestMapping("perm")
public class SysPermController {

    @Autowired
    private ISysPermService permService;


    @GetMapping("/")
    public String index(Model model) {
        SysUser user = TokenManager.getToken();
        model.addAttribute("perms", permService.getPerm(user.getUserId(), null, null));
        return "system/perm/perm";
    }

    @GetMapping("/edit")
    public String edit() {
        return "system/perm/edit";
    }

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @GetMapping("getPerm")
    @ResponseBody
    public Result list(String userId, String permName, Integer permType) {
        return new Result(permService.getPerm(userId, permName, permType));
    }

    @GetMapping("editPerm")
    @ResponseBody
    public Result edit(SysPerm perm) {
///		permService.editPerm(perm);
        permService.saveOrUpdate(perm);
        return new Result();
    }


}
