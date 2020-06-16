package com.dries.common.exception;

import com.dries.common.api.IErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 自定义API异常
 * @author: Dries
 * @date: 2020/6/16 23:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends RuntimeException {

    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
