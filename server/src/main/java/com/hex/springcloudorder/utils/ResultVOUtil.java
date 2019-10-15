package com.hex.springcloudorder.utils;

import com.hex.springcloudorder.vo.ResultVO;

/**
 * User: hexuan
 * Date: 2019/9/18
 * Time: 10:29 AM
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

}
