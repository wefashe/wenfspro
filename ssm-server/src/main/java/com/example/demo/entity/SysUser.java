package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wenfs
 * @since 2019-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private String userId;

    /**
     * 登录账户
     */
    private String userName;

    /**
     * 登录密码
     */
    private String userPass;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 密码加密盐
     */
    private String passSalt;

    /**
     * 用户状态 1-启用 0-停用
     */
    
    private String userStatus;

    /**
     * 性别 0-女，1-男
     */
    private Integer userSex;

    /**
     * 头像地址
     */
    private String headPortrait;

    /**
     * 手机
     */
    private String userPhone;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 生日
     */
    private String userBirthday;

    /**
     * 注册时间
     */
    private String createTime;

    /**
     * 最后修改时间
     */
    private String updateTime;

    /**
     * 最后登录时间
     */
    private String lastLoginTime;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 最后修改者的ip
     */
    private String updateIp;

    /**
     * 备注
     */
    private String remark;

}
