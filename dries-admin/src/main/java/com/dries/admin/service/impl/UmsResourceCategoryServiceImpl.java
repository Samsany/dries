package com.dries.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dries.admin.dao.UmsResourceCategoryDao;
import com.dries.admin.entity.UmsResourceCategoryEntity;
import com.dries.admin.service.UmsResourceCategoryService;
import org.springframework.stereotype.Service;


@Service("umsResourceCategoryService")
public class UmsResourceCategoryServiceImpl extends ServiceImpl<UmsResourceCategoryDao, UmsResourceCategoryEntity> implements UmsResourceCategoryService {


}