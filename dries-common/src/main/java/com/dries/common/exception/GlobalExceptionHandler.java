package com.dries.common.exception;

import com.dries.common.api.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 全局异常处理
 * @author: Dries
 * @date: 2020/6/16 23:46
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler
    public CommonResult handle(ApiException e){
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }

        return CommonResult.failed(e.getMessage());
    }

}
