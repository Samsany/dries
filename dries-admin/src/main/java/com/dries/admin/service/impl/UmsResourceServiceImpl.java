package com.dries.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dries.admin.dao.UmsResourceDao;
import com.dries.admin.entity.UmsResourceEntity;
import com.dries.admin.service.UmsAdminCacheService;
import com.dries.admin.service.UmsResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @author ZSY
 */
@Service("umsResourceService")
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceDao, UmsResourceEntity> implements UmsResourceService {

    @Resource
    private UmsResourceDao resourceDao;

    @Resource
    private UmsAdminCacheService adminCacheService;

    /**
     * 添加资源
     *
     * @param umsResource
     */
    @Override
    public int create(UmsResourceEntity umsResource) {

        umsResource.setCreateTime(new Date());

        return resourceDao.insert(umsResource);
    }
}