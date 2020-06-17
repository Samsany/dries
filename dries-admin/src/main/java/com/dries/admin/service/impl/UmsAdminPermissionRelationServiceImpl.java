package com.dries.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dries.admin.dao.UmsAdminPermissionRelationDao;
import com.dries.admin.entity.UmsAdminPermissionRelationEntity;
import com.dries.admin.service.UmsAdminPermissionRelationService;
import org.springframework.stereotype.Service;


@Service("umsAdminPermissionRelationService")
public class UmsAdminPermissionRelationServiceImpl extends ServiceImpl<UmsAdminPermissionRelationDao, UmsAdminPermissionRelationEntity> implements UmsAdminPermissionRelationService {

}