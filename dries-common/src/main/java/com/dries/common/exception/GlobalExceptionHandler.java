package com.dries.common.exception;

import com.dries.common.api.CommonResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

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

    /**
     * 统一表单验证
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult handleNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        return CommonResult.failed(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

}
