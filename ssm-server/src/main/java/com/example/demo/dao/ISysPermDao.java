package com.example.demo.dao;

import com.example.demo.entity.SysPerm;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author wenfs
 * @since 2019-02-13
 */
@Repository
public interface ISysPermDao extends BaseMapper<SysPerm> {

	List<SysPerm> getPerm(@Param("userId")String userId,@Param("permName")String permName,@Param("permType")Integer permType);

}
