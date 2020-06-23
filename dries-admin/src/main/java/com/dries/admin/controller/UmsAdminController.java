package com.dries.admin.controller;

import com.dries.admin.entity.UmsAdminEntity;
import com.dries.admin.entity.UmsPermissionEntity;
import com.dries.admin.form.AdminLoginForm;
import com.dries.admin.form.AdminRegisterFrom;
import com.dries.admin.service.UmsAdminService;
import com.dries.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 后台用户管理
 *
 * @author zsy
 * @email samphsanie@gmail.com
 * @date 2020-06-17 11:31:50
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "UmsAdminController", value = "后台用户管理")
public class UmsAdminController {

    @Resource
    private UmsAdminService adminService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult<UmsAdminEntity> register(@Valid @RequestBody AdminRegisterFrom adminForm) {
        return adminService.register(adminForm);
    }

    @ApiOperation(value = "登录后返回Token")
    @PostMapping("/login")
    public CommonResult login(@Valid @RequestBody AdminLoginForm loginForm) {
        return adminService.login(loginForm.getUsername(), loginForm.getPassword());
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @GetMapping("/permission/{adminId}")
    public CommonResult<List<UmsPermissionEntity>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermissionEntity> permissionList = adminService.getPermissions(adminId);
        return CommonResult.success(permissionList);
    }

}
