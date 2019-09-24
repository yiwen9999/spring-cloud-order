package com.hex.springcloudorder.vo;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * User: hexuan
 * Date: 2019/9/17
 * Time: 2:48 PM
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
