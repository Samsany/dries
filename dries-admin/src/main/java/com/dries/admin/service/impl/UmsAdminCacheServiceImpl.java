package com.dries.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.dries.admin.dao.UmsAdminRoleRelationDao;
import com.dries.admin.entity.UmsAdminEntity;
import com.dries.admin.entity.UmsAdminRoleRelationEntity;
import com.dries.admin.entity.UmsResourceEntity;
import com.dries.admin.service.UmsAdminCacheService;
import com.dries.admin.service.UmsAdminRoleRelationService;
import com.dries.admin.service.UmsAdminService;
import com.dries.security.service.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author ZSY
 * @createTime 2020/6/22 17:00
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {

    @Resource
    private UmsAdminService adminService;

    @Resource
    private RedisService redisService;

    @Resource
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    /**
     * 删除后台用户缓存
     *
     * @param adminId
     */
    @Override
    public void delAdmin(Long adminId) {
        UmsAdminEntity admin = adminService.getById(adminId);
        if (admin != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
            redisService.del(key);
        }
    }

    /**
     * 删除后台用户资源列表缓存
     *
     * @param adminId
     */
    @Override
    public void delResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.del(key);
    }

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     *
     * @param roleId
     */
    @Override
    public void delResourceListByRole(Long roleId) {
        List<UmsAdminRoleRelationEntity> list = new LambdaQueryChainWrapper<>(adminRoleRelationDao)
                .eq(UmsAdminRoleRelationEntity::getRoleId, roleId)
                .list();

        if (CollUtil.isNotEmpty(list)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = list.stream()
                    .map(e -> keyPrefix + e.getAdminId())
                    .collect(Collectors.toList());
            redisService.del(keys);
        }

    }

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     *
     * @param roleIds
     */
    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {
        List<UmsAdminRoleRelationEntity> list = new LambdaQueryChainWrapper<>(adminRoleRelationDao)
                .list();
    }

    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     *
     * @param resourceId
     */
    @Override
    public void delResourceListByResource(Long resourceId) {

    }

    /**
     * 获取缓存后台用户信息
     *
     * @param username
     */
    @Override
    public UmsAdminEntity getAdmin(String username) {
        return null;
    }

    /**
     * 设置缓存后台用户信息
     *
     * @param admin
     */
    @Override
    public void setAdmin(UmsAdminEntity admin) {

    }

    /**
     * 获取缓存后台用户资源列表
     *
     * @param adminId
     */
    @Override
    public List<UmsResourceEntity> getResourceList(Long adminId) {
        return null;
    }

    /**
     * 设置后台后台用户资源列表
     *
     * @param adminId
     * @param resourceList
     */
    @Override
    public void setResourceList(Long adminId, List<UmsResourceEntity> resourceList) {

    }
}
