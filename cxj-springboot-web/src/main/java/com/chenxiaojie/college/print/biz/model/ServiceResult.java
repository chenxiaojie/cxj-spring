package com.chenxiaojie.college.print.biz.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * Created by chenxiaojie on 15/7/16.
 * 公用的请求json结果类
 */
@Getter
@ApiModel(value = "通用返回实体")
public class ServiceResult<T> {

    public static final int INTERNAL_ERROR = 500;
    public static final int SUCCESS = 200;

    /**
     * 返回码,非200则出错
     */
    @ApiModelProperty(value = "状态，200=成功，500=失败", position = 1)
    private int code = SUCCESS;

    /**
     * 提示信息(大部分是报错信息,也可以是提示信息)
     */
    @ApiModelProperty(value = "报错信息", position = 2)
    private String msg;

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据实体", position = 3)
    private T data;

    private ServiceResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return code == SUCCESS;
    }

    public static <T> ServiceResult<T> success() {
        return new ServiceResult(SUCCESS, "", null);
    }

    public static <T> ServiceResult<T> success(T data) {
        return new ServiceResult(SUCCESS, "", data);
    }

    public static <T> ServiceResult<T> successMsg(String msg) {
        return new ServiceResult(SUCCESS, msg, null);
    }

    public static <T> ServiceResult<T> success(T data, String msg) {
        return new ServiceResult(SUCCESS, msg, data);
    }

    public static <T> ServiceResult<T> fail(String msg) {
        return new ServiceResult(INTERNAL_ERROR, msg, null);
    }
}
