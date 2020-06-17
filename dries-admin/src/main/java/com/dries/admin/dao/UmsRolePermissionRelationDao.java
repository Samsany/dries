package com.dries.admin.dao;

import com.dries.admin.entity.UmsRolePermissionRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户角色和权限关系表
 * 
 * @author zsy
 * @email samphsanie@gmail.com
 * @date 2020-06-17 11:31:51
 */
@Mapper
public interface UmsRolePermissionRelationDao extends BaseMapper<UmsRolePermissionRelationEntity> {
	
}
