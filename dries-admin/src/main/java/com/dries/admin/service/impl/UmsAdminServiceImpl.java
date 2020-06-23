package com.dries.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dries.admin.bo.AdminUserDetails;
import com.dries.admin.dao.UmsAdminDao;
import com.dries.admin.dao.UmsAdminRoleRelationDao;
import com.dries.admin.entity.UmsAdminEntity;
import com.dries.admin.entity.UmsPermissionEntity;
import com.dries.admin.entity.UmsResourceEntity;
import com.dries.admin.form.AdminRegisterFrom;
import com.dries.admin.service.UmsAdminCacheService;
import com.dries.admin.service.UmsAdminService;
import com.dries.common.api.CommonResult;
import com.dries.common.api.ResultCode;
import com.dries.common.exception.ApiException;
import com.dries.security.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author ZSY
 */
@Service("umsAdminService")
@Slf4j
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminDao, UmsAdminEntity> implements UmsAdminService {

    @Resource
    private UmsAdminDao umsAdminDao;
    @Resource
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private UmsAdminCacheService adminCacheService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * admin 注册
     *
     * @param adminForm
     * @return
     */
    @Override
    public CommonResult<UmsAdminEntity> register(AdminRegisterFrom adminForm) {

        UmsAdminEntity admin = new UmsAdminEntity();
        BeanUtils.copyProperties(adminForm, admin);

        // 查询用户名是否重复
        List<UmsAdminEntity> adminEntityList = umsAdminDao.selectList(new QueryWrapper<UmsAdminEntity>().eq("username", admin.getUsername()));

        if (adminEntityList.size() > 0) {
            return CommonResult.failed(ResultCode.REGISTER_USERNAME_ALREADY_EXISTS, ResultCode.REGISTER_USERNAME_ALREADY_EXISTS.getMessage());
        }

        // 密码加密
        String encode = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encode);

        int insertResult = umsAdminDao.insert(admin);
        if (insertResult == 0) {
            return CommonResult.failed(ResultCode.FAILED);
        }

        return CommonResult.success(admin, ResultCode.REGISTER_SUCCESS.getMessage());
    }

    /**
     * 登录功能
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {

        String token = null;
        try {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new ApiException(ResultCode.LOGIN_FAILED);
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);

        } catch (AuthenticationException e) {
            log.warn("【登录异常：{}】", e.getMessage());
        }
        return token;
    }

    /**
     * 获取用户所有权限（包括角色权限和 +- 权限）
     *
     * @param adminId
     * @return
     */
    @Override
    public List<UmsPermissionEntity> getPermissions(Long adminId) {
        return  adminRoleRelationDao.getPermissionList(adminId);
    }

    /**
     * 根据用户名获取后台管理员
     *
     * @param username
     * @return
     */
    @Override
    public CommonResult<UmsAdminEntity> getAdminByUsername(String username) {

        List<UmsAdminEntity> list = new LambdaQueryChainWrapper<>(umsAdminDao)
                .eq(UmsAdminEntity::getUsername, username)
                .list();

        if (list != null && list.size() > 0) {
            return CommonResult.success(list.get(0));
        }

        return CommonResult.failed();
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) {

        UmsAdminEntity admin = getAdminByUsername(username).getData();

        if (admin != null) {
            List<UmsResourceEntity> resources = getResourceList(admin.getId());
            return new AdminUserDetails(admin, resources);
        }

        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public List<UmsResourceEntity> getResourceList(Long adminId) {
        List<UmsResourceEntity> resourceList = adminCacheService.getResourceList(adminId);
        if(CollUtil.isNotEmpty(resourceList)){
            return  resourceList;
        }
        resourceList = adminRoleRelationDao.getResourceList(adminId);
        if(CollUtil.isNotEmpty(resourceList)){
            adminCacheService.setResourceList(adminId,resourceList);
        }
        return resourceList;
    }
}