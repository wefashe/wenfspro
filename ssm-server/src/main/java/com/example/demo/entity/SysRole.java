package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author wenfs
 * @since 2019-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色状态 1-启用 0-停用
     */
    private String roleStatus;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 最后修改时间
     */
    private String updateTime;

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
