package com.example.demo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author wenfs
 * @since 2019-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 上级部门id
     */
    private String parId;

    /**
     * 部门在当前层级下的顺序
     */
    private Long sequence;

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
