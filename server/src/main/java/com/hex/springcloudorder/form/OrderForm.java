package com.hex.springcloudorder.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * User: hexuan
 * Date: 2019/9/20
 * Time: 3:00 PM
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "openid必填")
    private String openid;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
