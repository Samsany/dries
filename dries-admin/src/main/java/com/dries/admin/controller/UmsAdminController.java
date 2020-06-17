package com.dries.admin.controller;

import com.dries.admin.entity.UmsAdminEntity;
import com.dries.admin.entity.UmsPermissionEntity;
import com.dries.admin.form.AdminLoginForm;
import com.dries.admin.form.AdminRegisterFrom;
import com.dries.admin.service.UmsAdminService;
import com.dries.common.api.CommonResult;
import com.dries.common.api.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult<UmsAdminEntity> register(@Valid @RequestBody AdminRegisterFrom adminForm) {
        return adminService.register(adminForm);
    }

    @ApiOperation(value = "登录后返回Token")
    @PostMapping("/login")
    public CommonResult login(@Valid @RequestBody AdminLoginForm loginForm,
                              Map<String, String> map) {
        String token = adminService.login(loginForm.getUsername(), loginForm.getPassword());
        if (token == null) {
            return CommonResult.failed(ResultCode.LOGIN_FAILED);
        }

        map.put("token",token);
        map.put("tokenHead",tokenHead);

        return CommonResult.success(map);
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @GetMapping("/permission/{adminId}")
    public CommonResult<List<UmsPermissionEntity>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermissionEntity> permissionList = adminService.getPermissions(adminId);
        return CommonResult.success(permissionList);
    }

}
