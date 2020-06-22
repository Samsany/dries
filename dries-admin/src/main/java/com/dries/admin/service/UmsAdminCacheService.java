package com.dries.admin.service;

import com.dries.admin.entity.UmsAdminEntity;
import com.dries.admin.entity.UmsResourceEntity;

import java.util.List;

/**
 * @Description:
 * @Author ZSY
 * @createTime 2020/6/22 16:58
 */
public interface UmsAdminCacheService {

    /**
     * 删除后台用户缓存
     */
    void delAdmin(Long adminId);

    /**
     * 删除后台用户资源列表缓存
     */
    void delResourceList(Long adminId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRole(Long roleId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRoleIds(List<Long> roleIds);

    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     */
    void delResourceListByResource(Long resourceId);

    /**
     * 获取缓存后台用户信息
     */
    UmsAdminEntity getAdmin(String username);

    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(UmsAdminEntity admin);

    /**
     * 获取缓存后台用户资源列表
     */
    List<UmsResourceEntity> getResourceList(Long adminId);

    /**
     * 设置后台后台用户资源列表
     */
    void setResourceList(Long adminId, List<UmsResourceEntity> resourceList);

}
