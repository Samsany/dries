package com.dries.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dries.admin.dao.UmsPermissionDao;
import com.dries.admin.entity.UmsPermissionEntity;
import com.dries.admin.service.UmsPermissionService;
import org.springframework.stereotype.Service;


@Service("umsPermissionService")
public class UmsPermissionServiceImpl extends ServiceImpl<UmsPermissionDao, UmsPermissionEntity> implements UmsPermissionService {


}