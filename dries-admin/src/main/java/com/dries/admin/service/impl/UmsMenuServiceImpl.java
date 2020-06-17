package com.dries.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dries.admin.dao.UmsMenuDao;
import com.dries.admin.entity.UmsMenuEntity;
import com.dries.admin.service.UmsMenuService;
import org.springframework.stereotype.Service;


@Service("umsMenuService")
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuDao, UmsMenuEntity> implements UmsMenuService {


}