package com.dries.common.exception;

import com.dries.common.api.IErrorCode;

/**
 * @description: 断言处理类，用于抛出各种API异常
 * @author: Dries
 * @date: 2020/6/17 0:03
 */
public class Asserts {

    public static void fail(String message){
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode){
        throw new ApiException(errorCode);
    }

}
