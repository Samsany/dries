package com.dries.admin.controller;

import com.dries.admin.entity.UmsResourceEntity;
import com.dries.admin.service.UmsResourceService;
import com.dries.common.api.CommonResult;
import com.dries.security.component.DynamicSecurityMetadataSource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 后台资源表
 *
 * @author zsy
 * @email samphsanie@gmail.com
 * @date 2020-06-17 11:31:51
 */
@RestController
@Api(tags = "UmsResourceController", description = "后台资源管理")
@RequestMapping("admin/umsresource")
public class UmsResourceController {

    @Resource
    private UmsResourceService resourceService;

    @Resource
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @ApiOperation("添加后台资源")
    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsResourceEntity umsResource) {
        int count = resourceService.create(umsResource);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
