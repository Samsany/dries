package com.dries.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)
 * 
 * @author zsy
 * @email samphsanie@gmail.com
 * @date 2020-06-17 11:31:51
 */
@Data
@TableName("ums_admin_permission_relation")
public class UmsAdminPermissionRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long adminId;
	/**
	 * 
	 */
	private Long permissionId;
	/**
	 * 
	 */
	private Integer type;

}
