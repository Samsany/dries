package com.dries.common.api;

/**
 * 枚举了一些常用API操作码
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    REGISTER_SUCCESS(200, "注册成功"),
    REGISTER_USERNAME_ALREADY_EXISTS(500, "注册失败,用户名已存在"),
    VALIDATE_FAILED(404, "参数检验失败"),
    LOGIN_FAILED(401,"用户名或密码错误"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
