package com.dries.admin.dao;

import com.dries.admin.entity.UmsMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台菜单表
 * 
 * @author zsy
 * @email samphsanie@gmail.com
 * @date 2020-06-17 11:31:50
 */
@Mapper
public interface UmsMenuDao extends BaseMapper<UmsMenuEntity> {
	
}
