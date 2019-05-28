package com.example.demo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author wenfs
 * @since 2019-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志id
     */
    @TableId
    private String logId;

    /**
     * 权限更新的类型，1：部门，2：用户，3：权限，4：角色，5：角色用户关系，6：角色权限关系
     */
    private Long targetType;

    /**
     * 对象id，比如用户、权限、角色等
     */
    private String targetId;

    /**
     * 旧值
     */
    private String oldValue;

    /**
     * 新值
     */
    private String newValue;

    /**
     * 当前是否复原过，0：没有，1：复原过
     */
    private Integer logStatus;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 最后修改时间
     */
    private String updateTime;

    /**
     * 最后修改者的ip
     */
    private String updateIp;


}
