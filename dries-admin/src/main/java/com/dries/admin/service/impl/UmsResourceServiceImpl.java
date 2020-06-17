package com.dries.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dries.admin.dao.UmsResourceDao;
import com.dries.admin.entity.UmsResourceEntity;
import com.dries.admin.service.UmsResourceService;
import org.springframework.stereotype.Service;


@Service("umsResourceService")
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceDao, UmsResourceEntity> implements UmsResourceService {


}