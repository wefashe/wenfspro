package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.util.enums.PermType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author wenfs
 * @since 2019-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysPerm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private String permId;

    /**
     * 权限名称
     */
    private String permName;

    /**
     * 权限url或其他
     */
    private String permUrl;

    /**
     * 权限图标 一般菜单权限才有
     */
    private String permIcon;

    /**
     * 权限状态 1-启用 0-停用
     */
    private String permStatus;

    /**
     * 上级权限id
     */
    private String parId;

    /**
     * 权限在当前模块下的顺序
     */
    private Long sequence;

    /**
     * 权限类型 1-菜单  2-按钮  3-其他
     */
    private Integer permType;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 最后修改时间
     */
    private String updateTime;

    /**
     * 最后修改者的ip
     */
    private String updateIp;

    /**
     * 备注
     */
    private String remark;


}
