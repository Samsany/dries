package com.dries.admin.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @Description: admin注册表单
 * @Author ZSY
 * @createTime 2020/6/17 13:13
 */
@Data
public class AdminRegisterFrom {

    /**
     * NotNull：不能为null，但可以为empty
     * NotEmpty：不能为null，而且长度必须大于0
     * NotBlank：只能作用在String上，不能为null，而且调用trim()后，长度必须大于0
     */

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private String icon;

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nickName;

    private String note;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private Integer status = 1;

}
