package com.dries.admin.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @Author ZSY
 * @createTime 2020/6/17 15:29
 */
@Data
public class AdminLoginForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
