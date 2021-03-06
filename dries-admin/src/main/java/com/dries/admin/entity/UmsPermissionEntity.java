package com.dries.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台用户权限表
 * 
 * @author zsy
 * @email samphsanie@gmail.com
 * @date 2020-06-17 11:31:51
 */
@Data
@TableName("ums_permission")
public class UmsPermissionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 父级权限id
	 */
	private Long pid;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 权限值
	 */
	private String value;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
	 */
	private Integer type;
	/**
	 * 前端资源路径
	 */
	private String uri;
	/**
	 * 启用状态；0->禁用；1->启用
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 排序
	 */
	private Integer sort;

}
