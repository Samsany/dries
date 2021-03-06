package com.dries.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dries.admin.entity.UmsResourceEntity;

/**
 * 后台资源表
 *
 * @author zsy
 * @email samphsanie@gmail.com
 * @date 2020-06-17 11:31:51
 */
public interface UmsResourceService extends IService<UmsResourceEntity> {

    /**
     * 添加资源
     */
    int create(UmsResourceEntity umsResource);
}

