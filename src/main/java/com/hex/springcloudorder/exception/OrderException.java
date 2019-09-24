package com.hex.springcloudorder.exception;

import com.hex.springcloudorder.enums.ResultEnum;

/**
 * User: hexuan
 * Date: 2019/9/20
 * Time: 3:52 PM
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
