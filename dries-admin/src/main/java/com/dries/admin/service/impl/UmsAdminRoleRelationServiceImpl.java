package com.dries.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dries.admin.dao.UmsAdminRoleRelationDao;
import com.dries.admin.entity.UmsAdminRoleRelationEntity;
import com.dries.admin.service.UmsAdminRoleRelationService;
import org.springframework.stereotype.Service;


@Service("umsAdminRoleRelationService")
public class UmsAdminRoleRelationServiceImpl extends ServiceImpl<UmsAdminRoleRelationDao, UmsAdminRoleRelationEntity> implements UmsAdminRoleRelationService {


}