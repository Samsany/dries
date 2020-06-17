package com.dries.admin.dao;

import com.dries.admin.entity.UmsAdminPermissionRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)
 * 
 * @author zsy
 * @email samphsanie@gmail.com
 * @date 2020-06-17 11:31:51
 */
@Mapper
public interface UmsAdminPermissionRelationDao extends BaseMapper<UmsAdminPermissionRelationEntity> {
	
}
