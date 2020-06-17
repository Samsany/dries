package com.dries.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dries.admin.dao.UmsRoleDao;
import com.dries.admin.entity.UmsRoleEntity;
import com.dries.admin.service.UmsRoleService;
import org.springframework.stereotype.Service;


@Service("umsRoleService")
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleDao, UmsRoleEntity> implements UmsRoleService {


}