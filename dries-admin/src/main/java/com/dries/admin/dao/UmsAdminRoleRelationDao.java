package com.dries.admin.dao;

import com.dries.admin.entity.UmsAdminRoleRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dries.admin.entity.UmsPermissionEntity;
import com.dries.admin.entity.UmsResourceEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 后台用户和角色关系表
 * 
 * @author zsy
 * @email samphsanie@gmail.com
 * @date 2020-06-17 11:31:50
 */
@Mapper
public interface UmsAdminRoleRelationDao extends BaseMapper<UmsAdminRoleRelationEntity> {

    List<UmsPermissionEntity> getPermissionList(Long adminId);

    List<Long> getAdminIdList(Long resourceId);

    List<UmsResourceEntity> getResourceList(Long adminId);
}
