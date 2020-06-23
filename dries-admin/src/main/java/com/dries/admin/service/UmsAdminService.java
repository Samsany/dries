package com.dries.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dries.admin.entity.UmsAdminEntity;
import com.dries.admin.entity.UmsPermissionEntity;
import com.dries.admin.entity.UmsResourceEntity;
import com.dries.admin.form.AdminRegisterFrom;
import com.dries.common.api.CommonResult;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * 后台用户表
 *
 * @author zsy
 * @email samphsanie@gmail.com
 * @date 2020-06-17 11:31:50
 */
public interface UmsAdminService extends IService<UmsAdminEntity> {

    /**
     * 注册功能
     * @param adminForm
     * @return
     */
    CommonResult<UmsAdminEntity> register(AdminRegisterFrom adminForm);

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限（包括角色权限和 +- 权限）
     * @param adminId
     * @return
     */
    List<UmsPermissionEntity> getPermissions(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResourceEntity> getResourceList(Long adminId);

    /**
     * 根据用户名获取后台管理员
     * @param username
     * @return
     */
    CommonResult<UmsAdminEntity> getAdminByUsername(String username);

    UserDetails loadUserByUsername(String username);
}

